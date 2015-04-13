package me.panavtec.cleancontacts.presentation.invoker;

import java.util.concurrent.ThreadFactory;

public class InteractorOutputThreadFactory implements ThreadFactory {
  @Override public Thread newThread(Runnable r) {
    Thread thread = new Thread(r, "InteractorInvoker" + r.toString());
    if (r instanceof InteractorOutputTask) {
      thread.setPriority(((InteractorOutputTask) r).getPriority());
    }
    return thread;
  }
}
