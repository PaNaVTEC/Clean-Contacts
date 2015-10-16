package me.panavtec.cleancontacts.modules.detail;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.panavtec.cleancontacts.R;

public class ContactInfoView extends LinearLayout {

  @Bind(R.id.infoLabel) TextView infoLabel;
  @Bind(R.id.infoValue) TextView infoValue;
  @Bind(R.id.icon) ImageView imageView;

  private Drawable iconDrawable;
  private String labelString;
  private String valueString;

  public ContactInfoView(Context context) {
    super(context);
    init(null);
  }

  public ContactInfoView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public ContactInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ContactInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
    initAttrs(attrs);
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View rootView = inflater.inflate(R.layout.contact_info_view, this, true);
    ButterKnife.bind(this, rootView);
    initView();
  }

  private void initAttrs(AttributeSet attrs) {
    if (attrs != null) {
      TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ContactInfoViewAttrs);
      iconDrawable = ta.getDrawable(R.styleable.ContactInfoViewAttrs_ci_info_icon);
      labelString = ta.getString(R.styleable.ContactInfoViewAttrs_ci_info_label);
      valueString = ta.getString(R.styleable.ContactInfoViewAttrs_ci_info_value);
      ta.recycle();
    }
  }

  private void initView() {
    initBackground();
    initPadding();
    initInfo();
    initIcon();
  }

  private void initBackground() {
    setBackground(getResources().getDrawable(R.drawable.list_selector));
  }

  private void initPadding() {
    int itemPadding = getResources().getDimensionPixelSize(R.dimen.item_padding);
    setPadding(itemPadding, itemPadding, itemPadding, itemPadding);
  }

  private void initInfo() {
    infoLabel.setText(labelString);
    infoValue.setText(valueString);
  }

  private void initIcon() {
    Drawable icon = iconDrawable.mutate();
    icon.setColorFilter(getResources().getColor(R.color.primary_dark), PorterDuff.Mode.SRC_IN);
    imageView.setImageDrawable(icon);
  }

  public void setInfoValue(String textStringRes) {
    infoValue.setText(textStringRes);
  }

  public void setInfoLabel(@StringRes int textStringRes) {
    infoLabel.setText(textStringRes);
  }

  public void setIconImage(@DrawableRes int iconDrawRes) {
    imageView.setImageResource(iconDrawRes);
  }
}
