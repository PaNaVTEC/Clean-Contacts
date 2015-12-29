package me.panavtec.cleancontacts.ui.items;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import java.util.Locale;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;

public class ContactViewHolder extends EasyViewHolder<PresentationContact> {

  @Bind(R.id.imageView) ImageView imageView;
  @Bind(R.id.nameTextView) TextView nameTextView;
  @Bind(R.id.phoneTextView) TextView phoneTextView;

  private ImageLoader imageLoader;
  private Drawable placeholder;

  public ContactViewHolder(Context context, ViewGroup parent, ImageLoader imageLoader) {
    super(context, parent, R.layout.item_contact);
    this.imageLoader = imageLoader;
    ButterKnife.bind(this, itemView);
    initPlaceHolder(context);
  }

  private void initPlaceHolder(Context context) {
    Drawable placeHolderResourceDrawable =
        ContextCompat.getDrawable(context, R.drawable.ic_contact_placerholder);
    int accentColor = ContextCompat.getColor(context, R.color.accent);
    placeholder = placeHolderResourceDrawable.mutate();
    placeholder.setColorFilter(accentColor, PorterDuff.Mode.SRC_IN);
  }

  @Override public void bindTo(PresentationContact contact) {
    nameTextView.setText(
        String.format(Locale.getDefault(), "%s, %s %s", contact.getTitle(), contact.getFirstName(),
            contact.getLastName()));
    imageLoader.load(contact.getPicture().getThumbnail(), imageView, placeholder);
    phoneTextView.setText(contact.getPhone());
  }
}
