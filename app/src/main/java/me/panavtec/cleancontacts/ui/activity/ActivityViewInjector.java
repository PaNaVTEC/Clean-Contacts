package me.panavtec.cleancontacts.ui.activity;

import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;

public class ActivityViewInjector {

  public void inject(ActionBarActivity activity, int layoutId) {
    if (layoutId != 0) {
      activity.setContentView(layoutId);
      ButterKnife.inject(activity);
    }
  }
}
