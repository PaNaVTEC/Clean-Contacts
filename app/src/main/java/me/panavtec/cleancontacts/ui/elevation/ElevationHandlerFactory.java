package me.panavtec.cleancontacts.ui.elevation;

import android.os.Build;
import javax.inject.Inject;
import me.panavtec.cleancontacts.di.qualifiers.ApiLevel;

public class ElevationHandlerFactory implements ElevationHandler.Factory {

  private int version;

  @Inject public ElevationHandlerFactory(@ApiLevel int version) {
    this.version = version;
  }
  
  @Override public ElevationHandler createElevationHandler() {
    if (version >= Build.VERSION_CODES.LOLLIPOP) {
      return new LollipopElevationHandler();
    } else {
      return new NoElevationHandler();
    }
  }
}
