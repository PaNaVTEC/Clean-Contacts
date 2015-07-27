package me.panavtec.cleancontacts.domain.invoker;

import android.util.Log;

public class LogExceptionHandler implements Thread.UncaughtExceptionHandler {

  private static final String TAG = "LogExceptionHandler";

  @Override public void uncaughtException(Thread thread, Throwable ex) {
    Log.e(TAG, "Unhandled Interactor Exception", ex);
  }
}
