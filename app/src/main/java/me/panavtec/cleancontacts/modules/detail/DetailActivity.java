package me.panavtec.cleancontacts.modules.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;
import me.panavtec.cleancontacts.presentation.detail.DetailView;
import me.panavtec.cleancontacts.ui.BaseActivity;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;

public class DetailActivity extends BaseActivity implements DetailView {

    private static final String CONTACT_MD5_EXTRA = "ContactExtra";

    @Inject
    DetailPresenter presenter;
    @Inject
    ImageLoader imageLoader;
    @Inject
    ErrorManager errorManager;

    @InjectView(R.id.contactImage)
    ImageView contactImageView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.nameTextView)
    TextView nameTextView;
    @InjectView(R.id.button_floating_action)
    FloatingActionButton buttonFloatingAction;

    private Contact contact;
    private String contactMd5;

    public static void launch(Activity from, String contactMd5, Pair<View, String>... sharedElements) {
        Intent intent = new Intent(from, DetailActivity.class);
        intent.putExtra(CONTACT_MD5_EXTRA, contactMd5);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(from, sharedElements);
        from.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArguments();
        initUi();
        presenter.onCreate(contactMd5);
    }

    private void parseArguments() {
        contactMd5 = getIntent().getStringExtra(CONTACT_MD5_EXTRA);
    }

    private void initUi() {
        initToolbar();
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int onCreateViewId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showContactData(Contact contact) {
        this.contact = contact;
        showImageView();
        showContactName();
    }

    private void showImageView() {
        imageLoader.load(contact.getPicture().getLarge(), contactImageView);
    }

    private void showContactName() {
        nameTextView.setText(contact.getName().getFullName());
    }

    @Override
    public void showGetContactError() {
        errorManager.showError(getString(R.string.err_getting_contacts));
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DetailModule(this));
    }

}
