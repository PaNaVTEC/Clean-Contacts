package me.panavtec.cleancontacts.domain.invoker;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class InteractorPriorityBlockingQueue extends PriorityBlockingQueue<Runnable> {

  public InteractorPriorityBlockingQueue(int initialCapacity) {
    super(initialCapacity, new Comparator<Runnable>() {
      @Override public int compare(Runnable runnable1, Runnable runnable2) {
        boolean firstIsPriority = runnable1 instanceof PriorityRunnableFutureDecorated;
        boolean secondIsPriority = runnable2 instanceof PriorityRunnableFutureDecorated;

        if (!firstIsPriority && !secondIsPriority) {
          return 0;
        } else if (!firstIsPriority) {
          return -1;
        } else if (!secondIsPriority) {
          return 1;
        }

        int priority1 = ((PriorityRunnableFutureDecorated) runnable1).getPriority();
        int priority2 = ((PriorityRunnableFutureDecorated) runnable2).getPriority();

        return Integer.valueOf(priority1).compareTo(priority2);
      }
    });
  }
}
