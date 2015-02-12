package me.panavtec.cleancontacts.modules.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.InjectView;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.modules.main.adapters.ContactAdapter;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;
import me.panavtec.cleancontacts.ui.BaseActivity;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject MainPresenter presenter;
    @Inject ErrorManager errorManager;
    @Inject ImageLoader imageLoader;

    @InjectView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.recyclerView) RecyclerView recyclerView;

    private ContactAdapter contactAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        presenter.onCreate();
    }

    private void initUi() {
        contactAdapter = new ContactAdapter(this, imageLoader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
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
        contactAdapter.addAll(contacts);
        contactAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override public void clearData() {
        contactAdapter.clear();
        contactAdapter.notifyDataSetChanged();
    }

    @Override public void showGetContactsError() {
        errorManager.showError(getString(R.string.err_getting_contacts));
    }

    @Override public void onRefresh() {
        presenter.onRefresh();
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }
    
}
