package me.panavtec.cleancontacts.ui.elevation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import me.panavtec.cleancontacts.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LollipopElevationHandler extends NoElevationHandler {

  public LollipopElevationHandler(Context context) {
    super(context);
  }

  @Override public void setElevation(View view, float elevation) {
    view.setElevation(elevation);
  }

  @Override public void setDefaultElevation(View view) {
    view.setElevation(view.getResources().getDimensionPixelSize(R.dimen.default_elevation));
  }
}
