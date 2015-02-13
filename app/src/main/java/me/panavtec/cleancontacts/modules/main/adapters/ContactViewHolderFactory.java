package me.panavtec.cleancontacts.modules.main.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.carlosdelachica.easyrecycleradapters.adapter.BaseEasyViewHolderFactory;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;

import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.items.ContactViewHolder;

public class ContactViewHolderFactory extends BaseEasyViewHolderFactory {

    private final ImageLoader imageLoader;

    public ContactViewHolderFactory(Context context, ImageLoader imageLoader) {
        super(context);
        this.imageLoader = imageLoader;
    }

    @Override
    public EasyViewHolder create(Class valueClass, ViewGroup parent) {
        return new ContactViewHolder(context, parent, imageLoader);
    }

}

