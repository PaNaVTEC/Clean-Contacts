package me.panavtec.cleancontacts.ui.transitions;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Transition;
import android.view.Window;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LollipopWindowTransitionListener implements WindowTransitionListener {
  
  private WindowTransitionEndListener endListener;
  private Window window;

  public LollipopWindowTransitionListener(WindowTransitionEndListener endListener, Window window) {
    this.endListener = endListener;
    this.window = window;
  }

  public boolean start() {
    final Transition transition = window.getSharedElementEnterTransition();
    if (transition != null) {
      transition.addListener(new Transition.TransitionListener() {

        @Override public void onTransitionEnd(Transition transition) {
          notificateEnd();
          transition.removeListener(this);
        }

        @Override public void onTransitionStart(Transition transition) {
        }

        @Override public void onTransitionCancel(Transition transition) {
          transition.removeListener(this);
        }

        @Override public void onTransitionPause(Transition transition) {
        }

        @Override public void onTransitionResume(Transition transition) {
        }
      });
      return true;
    }

    // If we reach here then we have not added a listener
    notificateEnd();
    return false;
  }

  private void notificateEnd() {
    if (endListener != null) {
      endListener.onEndTransition();
    }
  }
  
}
