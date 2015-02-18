package me.panavtec.cleancontacts.ui.items;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.entities.Name;
import me.panavtec.cleancontacts.domain.entities.Picture;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;

import java.util.Locale;

public class ContactViewHolder extends EasyViewHolder<Contact> {

    @InjectView(R.id.imageView) ImageView imageView;
    @InjectView(R.id.nameTextView) TextView nameTextView;
    @InjectView(R.id.phoneTextView) TextView phoneTextView;

    private ImageLoader imageLoader;
    private Drawable placeHolderDrawable;

    public ContactViewHolder(Context context, ViewGroup parent, ImageLoader imageLoader) {
        super(context, parent, R.layout.item_contact);
        this.imageLoader = imageLoader;
        ButterKnife.inject(this, itemView);
        initPlaceHolder(context);
    }

    private void initPlaceHolder(Context context) {
        Drawable placeHolderResourceDrawable = context.getResources().getDrawable(R.drawable.ic_contact_placerholder);
        int accentColor = context.getResources().getColor(R.color.accent);
        placeHolderDrawable = placeHolderResourceDrawable.mutate();
        placeHolderDrawable.setColorFilter(accentColor, PorterDuff.Mode.SRC_IN);

    }

    @Override public void bindTo(Contact contact) {
        Name name = contact.getName();
        nameTextView.setText(String.format(Locale.getDefault(), "%s, %s %s", name.getTitle(), name.getFirst(), name.getLast()));
        Picture picture = contact.getPicture();
        imageLoader.loadCircular(picture.getLarge(), imageView, placeHolderDrawable);
        phoneTextView.setText(contact.getPhone());
    }
    
}
