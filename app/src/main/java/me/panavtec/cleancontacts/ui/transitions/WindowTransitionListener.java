package me.panavtec.cleancontacts.ui.transitions;

public interface WindowTransitionListener {
  public boolean start();
  public void setupListener(WindowTransitionEndListener endListener);
  
  public static interface WindowTransitionEndListener {
    void onEndTransition();
  }
  
  public static interface Factory {
    WindowTransitionListener createWindowTransitionListener();
  }
}
