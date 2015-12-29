package me.panavtec.cleancontacts.ui.elevation;

import android.view.View;

public interface ElevationHandler {
  void setElevation(View view, float elevation);
  void setDefaultElevation(View view);
  
  interface Factory {
    ElevationHandler createElevationHandler();
  }
}
