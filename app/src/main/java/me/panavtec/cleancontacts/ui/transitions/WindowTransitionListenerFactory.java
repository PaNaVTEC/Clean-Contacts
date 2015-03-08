package me.panavtec.cleancontacts.ui.transitions;

import android.os.Build;
import android.view.Window;
import javax.inject.Inject;
import me.panavtec.cleancontacts.di.qualifiers.ApiLevel;

public class WindowTransitionListenerFactory implements WindowTransitionListener.Factory {

  private final int version;
  private final Window window;

  @Inject public WindowTransitionListenerFactory(@ApiLevel int version, Window window) {
    this.version = version;
    this.window = window;
  }
  
  @Override public WindowTransitionListener createWindowTransitionListener() {
    if (version >= Build.VERSION_CODES.LOLLIPOP) {
      return new LollipopWindowTransitionListener(window);
    } else {
      return new NoWindowTransitionListener();
    }
  }
}
