package me.panavtec.cleancontacts.modules.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.items.ContactViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contacts = new ArrayList<>();
    private Context context;
    private ImageLoader imageLoader;

    public ContactAdapter(Context context, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(context, parent);
    }

    @Override public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bindContact(contacts.get(position), imageLoader);
    }

    @Override public int getItemCount() {
        return contacts.size();
    }

    public void addAll(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }
    
    public void clear() {
        this.contacts.clear();
    }
    
}