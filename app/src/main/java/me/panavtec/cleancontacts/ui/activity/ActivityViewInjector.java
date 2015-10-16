package me.panavtec.cleancontacts.ui.activity;

import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

public class ActivityViewInjector {

  public void inject(AppCompatActivity activity, int layoutId) {
    if (layoutId != 0) {
      activity.setContentView(layoutId);
      ButterKnife.bind(activity);
    }
  }
}
