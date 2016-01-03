package me.panavtec.cleancontacts.ui.transitions;

public interface WindowTransitionListener {
  boolean start();
  void setupListener(WindowTransitionEndListener endListener);
  
  interface WindowTransitionEndListener {
    void onEndTransition();
  }
  
  interface Factory {
    WindowTransitionListener createWindowTransitionListener();
  }
}
