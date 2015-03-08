package me.panavtec.cleancontacts.ui.transitions;

public interface WindowTransitionListener {
  public boolean start();
  
  static interface WindowTransitionEndListener {
    void onEndTransition();
  }
}
