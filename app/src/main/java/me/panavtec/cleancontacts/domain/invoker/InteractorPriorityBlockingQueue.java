package me.panavtec.cleancontacts.domain.invoker;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class InteractorPriorityBlockingQueue extends PriorityBlockingQueue<Runnable> {

  public InteractorPriorityBlockingQueue(int initialCapacity) {
    super(initialCapacity, new Comparator<Runnable>() {
      @Override public int compare(Runnable runnable1, Runnable runnable2) {
        if (runnable1 instanceof InteractorOutputTask
            && runnable2 instanceof InteractorOutputTask) {
          return Integer.valueOf(((InteractorOutputTask) runnable1).getPriority())
              .compareTo(((InteractorOutputTask) runnable2).getPriority());
        }
        return 0;
      }
    });
  }
}
