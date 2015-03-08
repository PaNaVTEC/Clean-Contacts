package me.panavtec.cleancontacts.modules.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.carlosdelachica.easyrecycleradapters.recycler_view_manager.EasyRecyclerViewManager;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.modules.detail.DetailActionCommand;
import me.panavtec.cleancontacts.modules.main.adapters.ContactViewHolderFactory;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;
import me.panavtec.cleancontacts.ui.BaseActivity;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandler;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.items.ContactViewHolder;

public class MainActivity extends BaseActivity
    implements MainView, SwipeRefreshLayout.OnRefreshListener, EasyViewHolder.OnItemClickListener {

  @Inject MainPresenter presenter;
  @Inject ErrorManager errorManager;
  @Inject ImageLoader imageLoader;
  @Inject ElevationHandler elevationHandler;

  @InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
  @InjectView(R.id.recyclerView) RecyclerView recyclerView;
  @InjectView(R.id.empty_list) TextView emptyList;
  @InjectView(R.id.toolbar) Toolbar toolbar;

  private EasyRecyclerViewManager recyclerViewManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initUi();
    presenter.onCreate();
  }

  private void initUi() {
    initToolbar();
    initRecyclerView();
    initRefreshLayout();
  }

  private void initToolbar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      elevationHandler.setDefaultElevation(toolbar);
    }
  }

  private void initRecyclerView() {
    ContactViewHolderFactory contactViewHolderFactory =
        new ContactViewHolderFactory(this, imageLoader);
    EasyRecyclerAdapter adapter =
        new EasyRecyclerAdapter(contactViewHolderFactory, Contact.class, ContactViewHolder.class);
    recyclerViewManager =
        new EasyRecyclerViewManager.Builder(recyclerView, adapter).emptyLoadingListTextView(
            emptyList)
            .loadingListTextColor(android.R.color.black)
            .divider(R.drawable.list_divider)
            .clickListener(this)
            .build();
  }

  private void initRefreshLayout() {
    swipeRefreshLayout.setOnRefreshListener(this);
    recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      }

      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int topRowVerticalPosition = recyclerView == null || recyclerView.getChildCount() == 0 ? 0
            : recyclerView.getChildAt(0).getTop();
        swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
      }
    });
  }

  @Override public int onCreateViewId() {
    return R.layout.activity_main;
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override public void refreshContactsList(List<Contact> contacts) {
    recyclerViewManager.addAll(contacts);
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showGetContactsError() {
    errorManager.showError(getString(R.string.err_getting_contacts));
  }

  @Override public void onRefresh() {
    presenter.onRefresh();
  }

  @Override public void refreshUi() {
    recyclerViewManager.onRefresh();
    swipeRefreshLayout.setRefreshing(true);
  }

  @Override protected List<Object> getModules() {
    return Arrays.<Object>asList(new MainModule(this));
  }

  @Override public void onItemClick(int position, View view) {
    Contact contact = (Contact) recyclerViewManager.getItem(position);
    DetailActionCommand detailActionCommand =
        new DetailActionCommand(this, contact.getMd5(), contact.getPicture().getThumbnail(),
            (ImageView) view.findViewById(R.id.imageView),
            (TextView) view.findViewById(R.id.nameTextView));
    detailActionCommand.execute();
  }
}
