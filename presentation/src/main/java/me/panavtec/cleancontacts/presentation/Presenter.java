package me.panavtec.cleancontacts.presentation;

public interface Presenter<V extends PresenterView> {

  public void onCreate(V view);

  public void onResume();

  public void onPause();

  public void onDestroy();
}
