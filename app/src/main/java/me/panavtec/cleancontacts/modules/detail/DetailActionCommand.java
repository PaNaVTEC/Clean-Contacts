package me.panavtec.cleancontacts.modules.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import me.panavtec.cleancontacts.R;
import me.panavtec.cleancontacts.ui.ActionCommand;

public class DetailActionCommand implements ActionCommand {

  private Activity activity;
  private String contactMd5;
  private String thumbnail;
  private ImageView imageView;
  private TextView nameTextView;

  public DetailActionCommand(Activity activity, String contactMd5, String thumbnail,
      ImageView imageView, TextView nameTextView) {
    this.activity = activity;
    this.contactMd5 = contactMd5;
    this.thumbnail = thumbnail;
    this.imageView = imageView;
    this.nameTextView = nameTextView;
  }

  @Override public void execute() {
    Intent intent = new Intent(activity, DetailActivity.class);
    intent.putExtra(DetailActivity.CONTACT_MD5_EXTRA, contactMd5);
    intent.putExtra(DetailActivity.CONTACT_THUMBNAIL_EXTRA, thumbnail);
    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
        new Pair<View, String>(imageView, activity.getString(R.string.anim_picture)),
        new Pair<View, String>(nameTextView, activity.getString(R.string.anim_name)));
    activity.startActivity(intent, options.toBundle());
  }
}
