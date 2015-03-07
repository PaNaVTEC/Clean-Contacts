package me.panavtec.cleancontacts.modules.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.entities.Location;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;
import me.panavtec.cleancontacts.presentation.detail.DetailView;
import me.panavtec.cleancontacts.ui.BaseActivity;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.helper_util.HelperUtil;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;

public class DetailActivity extends BaseActivity implements DetailView {

    public static final String CONTACT_MD5_EXTRA = "ContactExtra";

    @Inject
    DetailPresenter presenter;
    @Inject ImageLoader imageLoader;
    @Inject ErrorManager errorManager;
    @Inject HelperUtil helperUtil;

    @InjectView(R.id.contactImage)
    ImageView contactImageView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.nameTextView)
    TextView nameTextView;
    @InjectView(R.id.phoneInfoView)
    ContactInfoView phoneInfoView;
    @InjectView(R.id.cellInfoView)
    ContactInfoView cellInfoView;
    @InjectView(R.id.emailInfoView)
    ContactInfoView emailInfoView;
    @InjectView(R.id.addressInfoView)
    ContactInfoView addressInfoView;

    private Contact contact;
    private String contactMd5;

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
        showMobilePhone();
        showPhone();
        showEmail();
        showAddress();
    }

    private void showImageView() {
        imageLoader.load(contact.getPicture().getLarge(), contactImageView);
    }

    private void showContactName() {
        nameTextView.setText(contact.getName().getFullName());
    }

    private void showMobilePhone() {
        cellInfoView.setInfoValue(contact.getCell());
    }

    private void showPhone() {
        phoneInfoView.setInfoValue(contact.getPhone());
    }

    private void showEmail() {
        emailInfoView.setInfoValue(contact.getEmail());
    }

    private void showAddress() {
        Location location = contact.getLocation();
        String address = new StringBuilder(location.getStreet()).append(", ").append(location.getCity()).append(", ").append(location.getZip()).append(", ").append(location.getState()).toString();
        addressInfoView.setInfoValue(address);
    }

    @Override
    public void showGetContactError() {
        errorManager.showError(getString(R.string.err_getting_contacts));
    }

    @OnClick(R.id.phoneInfoView)
    public void phoneClicked(){
        errorManager.showError(getString(R.string.just_a_sample));
    }

    @OnClick(R.id.emailInfoView)
    public void emailClicked(){
        errorManager.showError(getString(R.string.just_a_sample));
    }

    @OnClick(R.id.cellInfoView)
    public void cellClicked(){
        errorManager.showError(getString(R.string.just_a_sample));
    }

    @OnClick(R.id.button_floating_action)
    public void favouriteClicked(){
        errorManager.showError(getString(R.string.just_a_sample));
    }

    @OnClick(R.id.addressInfoView)
    public void addressClicked(){
        errorManager.showError(getString(R.string.just_a_sample));
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DetailModule(this));
    }

}
