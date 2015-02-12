package me.panavtec.cleancontacts.ui.imageloader;

import android.widget.ImageView;

public interface ImageLoader {
    public void load(String url, ImageView imageView);
    public void loadCircular(String url, ImageView imageView);
}
