package me.panavtec.cleancontacts.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageLoader {
    public void load(String url, ImageView imageView, Drawable placeHolderDrawable);
    public void load(String url, ImageView imageView);
    public void loadCircular(String url, ImageView imageView, Drawable placeHolderDrawable);
    public void loadCircular(String url, ImageView imageView);
}
