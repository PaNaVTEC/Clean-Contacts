package me.panavtec.cleancontacts.modules.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.panavtec.cleancontacts.ui.ActionCommand;

import javax.inject.Inject;

public class DetailActionCommand implements ActionCommand {

    private Activity activity;
    private String contactMd5;
    private ImageView imageView;
    private TextView nameTextView;

    @Inject public DetailActionCommand(Activity activity, String contactMd5, ImageView imageView, TextView nameTextView) {
        this.activity = activity;
        this.contactMd5 = contactMd5;
        this.imageView = imageView;
        this.nameTextView = nameTextView;
    }
    
    @Override public void execute() {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.CONTACT_MD5_EXTRA, contactMd5);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, 
                new Pair<View, String>(imageView, "picture"),
                new Pair<View, String>(nameTextView, "name"));
        activity.startActivity(intent, options.toBundle());
    }

}
