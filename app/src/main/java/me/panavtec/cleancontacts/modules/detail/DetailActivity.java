package me.panavtec.cleancontacts.modules.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import javax.inject.Inject;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;
import me.panavtec.cleancontacts.presentation.detail.DetailView;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.PresentationLocation;
import me.panavtec.cleancontacts.ui.BaseActivity;
import me.panavtec.cleancontacts.ui.Coordinator;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListener;

public class DetailActivity extends BaseActivity
    implements DetailView, Coordinator.CoordinatorCompleteAction,
    WindowTransitionListener.WindowTransitionEndListener {

  public static final String CONTACT_MD5_EXTRA = "ContactExtra";
  public static final String CONTACT_THUMBNAIL_EXTRA = "ContactThumbnailExtra";

  private static final String COORDINATE_END_TRANSITION = "COORDINATE_END_TRANSITION";
  private static final String COORDINATE_SHOW_CONTACT = "COORDINATE_SHOW_CONTACT";

  @Inject DetailPresenter presenter;
  @Inject ImageLoader imageLoader;
  @Inject ErrorManager errorManager;
  @Inject WindowTransitionListener windowTransitionListener;
  @InjectView(R.id.contactImage) ImageView contactImageView;

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.nameTextView) TextView nameTextView;
  @InjectView(R.id.phoneInfoView) ContactInfoView phoneInfoView;
  @InjectView(R.id.cellInfoView) ContactInfoView cellInfoView;
  @InjectView(R.id.emailInfoView) ContactInfoView emailInfoView;
  @InjectView(R.id.addressInfoView) ContactInfoView addressInfoView;
  
  private PresentationContact contact;
  private Coordinator coordinator;
  private String contactMd5;
  private String thumbnail;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initTransitionElements();
    parseArguments();
    presenter.onCreate(this);
  }

  @Override public int onCreateViewId() {
    return R.layout.activity_detail;
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
    presenter.obtainContact(contactMd5);
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  private void initTransitionElements() {
    coordinator = new Coordinator(this, COORDINATE_END_TRANSITION, COORDINATE_SHOW_CONTACT);
    windowTransitionListener.setupListener(this);
    imageLoader.loadWithoutEffects(thumbnail, contactImageView);
    windowTransitionListener.start();
  }

  private void parseArguments() {
    contactMd5 = getIntent().getStringExtra(CONTACT_MD5_EXTRA);
    thumbnail = getIntent().getStringExtra(CONTACT_THUMBNAIL_EXTRA);
  }

  @Override public void initUi() {
    initToolbar();
  }

  private void initToolbar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override public void showContactData(PresentationContact contact) {
    this.contact = contact;
    coordinator.completeAction(COORDINATE_SHOW_CONTACT);
    showContactName();
    showContactMobilePhone();
    showContactPhone();
    showContactEmail();
    showAddress();
  }

  private void showFullImage() {
    imageLoader.loadWithoutEffects(contact.getPicture().getLarge(), contactImageView);
  }

  private void showContactName() {
    nameTextView.setText(contact.getFirstName() + " " + contact.getLastName());
  }

  private void showContactMobilePhone() {
    cellInfoView.setInfoValue(contact.getCell());
  }

  private void showContactPhone() {
    phoneInfoView.setInfoValue(contact.getPhone());
  }

  private void showContactEmail() {
    emailInfoView.setInfoValue(contact.getEmail());
  }

  private void showAddress() {
    PresentationLocation location = contact.getLocation();
    String address = location.getStreet()
        + ", "
        + location.getCity()
        + ", "
        + location.getZip()
        + ", "
        + location.getState();
    addressInfoView.setInfoValue(address);
  }

  @Override public void onCoordinatorComplete() {
    showFullImage();
  }

  @Override public void showGetContactError() {
    errorManager.showError(getString(R.string.err_getting_contacts));
  }

  @OnClick(R.id.phoneInfoView) public void phoneClicked() {
    errorManager.showError(getString(R.string.just_a_sample));
  }

  @OnClick(R.id.emailInfoView) public void emailClicked() {
    errorManager.showError(getString(R.string.just_a_sample));
  }

  @OnClick(R.id.cellInfoView) public void cellClicked() {
    errorManager.showError(getString(R.string.just_a_sample));
  }

  @OnClick(R.id.button_floating_action) public void favouriteClicked() {
    errorManager.showError(getString(R.string.just_a_sample));
  }

  @OnClick(R.id.addressInfoView) public void addressClicked() {
    errorManager.showError(getString(R.string.just_a_sample));
  }

  @Override public void onEndTransition() {
    coordinator.completeAction(COORDINATE_END_TRANSITION);
  }

  @Override protected Object newDiModule() {
    return new DetailModule(this);
  }
}
