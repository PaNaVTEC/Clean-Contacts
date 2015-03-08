package me.panavtec.cleancontacts.ui.transitions;

public class NoWindowTransitionListener implements WindowTransitionListener {

  private WindowTransitionEndListener endListener;

  public NoWindowTransitionListener(WindowTransitionEndListener endListener) {
    this.endListener = endListener;
  }

  @Override public boolean start() {
    endListener.onEndTransition();
    return false;
  }
  
}
