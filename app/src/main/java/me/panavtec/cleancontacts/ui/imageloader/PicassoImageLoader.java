package me.panavtec.cleancontacts.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

  private Picasso picasso;

  public PicassoImageLoader(Picasso picasso) {
    this.picasso = picasso;
  }

  public void load(String url, ImageView imageView) {
    picasso.load(url).into(imageView);
  }

  @Override public void load(String url, ImageView imageView, Drawable placeholder) {
    picasso.load(url).placeholder(placeholder).into(imageView);
  }

  @Override public void loadWithoutEffects(String url, ImageView imageView) {
    picasso.load(url).noFade().noPlaceholder().into(imageView);
  }

  @Override public void loadCircular(String url, ImageView imageView, Drawable placeholder) {
    picasso.load(url).transform(new CircleTransform()).placeholder(placeholder).into(imageView);
  }
}
