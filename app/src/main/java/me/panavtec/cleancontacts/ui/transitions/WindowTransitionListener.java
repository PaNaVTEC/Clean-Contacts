package me.panavtec.cleancontacts.ui.transitions;

public interface WindowTransitionListener {
  public boolean start();
  public void setupListener(WindowTransitionEndListener endListener);
  
  static interface WindowTransitionEndListener {
    void onEndTransition();
  }
}
