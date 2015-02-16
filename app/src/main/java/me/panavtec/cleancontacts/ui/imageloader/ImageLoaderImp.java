package me.panavtec.cleancontacts.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImageLoaderImp implements ImageLoader {

    private Picasso picasso;

    public ImageLoaderImp(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView, Drawable placeHolderResId) {
        picasso.load(url).placeholder(placeHolderResId).into(imageView);
    }

    public void load(String url, ImageView imageView) {
        picasso.load(url).into(imageView);
    }

    @Override public void loadCircular(String url, ImageView imageView) {
        picasso.load(url).transform(new CircleTransform()).into(imageView);
    }

}
