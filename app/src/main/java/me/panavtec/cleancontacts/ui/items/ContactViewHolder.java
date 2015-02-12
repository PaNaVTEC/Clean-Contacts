package me.panavtec.cleancontacts.ui.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.entities.Name;
import me.panavtec.cleancontacts.domain.entities.Picture;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;

import java.util.Locale;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.imageView) ImageView imageView;
    @InjectView(R.id.nameTextView) TextView nameTextView;
    @InjectView(R.id.phoneTextView) TextView phoneTextView;

    public ContactViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false));
        ButterKnife.inject(this, itemView);
    }

    public void bindContact(Contact contact, ImageLoader imageLoader) {
        Name name = contact.getName();
        nameTextView.setText(String.format(Locale.getDefault(), "%s, %s %s", name.getTitle(), name.getFirst(), name.getLast()));
        Picture picture = contact.getPicture();
        imageLoader.loadCircular(picture.getThumbnail(), imageView);
        phoneTextView.setText(contact.getPhone());
    }

}
