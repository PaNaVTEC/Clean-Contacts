package me.panavtec.cleancontacts.ui.transitions;

public class NoWindowTransitionListener implements WindowTransitionListener {

  private WindowTransitionEndListener endListener;

  @Override public void setupListener(WindowTransitionEndListener endListener) {
    this.endListener = endListener;
  }

  @Override public boolean start() {
    if (endListener != null) {
      endListener.onEndTransition();
    }
    return false;
  }
  
}
