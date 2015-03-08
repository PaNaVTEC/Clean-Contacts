package me.panavtec.cleancontacts.ui.elevation;

import android.content.Context;
import android.view.View;

public class NoElevationHandler implements ElevationHandler {

  protected Context context;

  public NoElevationHandler(Context context) {
    this.context = context;
  }

  @Override public void setElevation(View view, float elevation) {
  }

  @Override public void setDefaultElevation(View view) {
  }
}
