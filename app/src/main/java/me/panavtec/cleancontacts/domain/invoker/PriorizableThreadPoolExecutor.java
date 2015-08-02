package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PriorizableThreadPoolExecutor extends ThreadPoolExecutor {

  public PriorizableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
  }

  @Override protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
    RunnableFuture<T> runnableFuture = super.newTaskFor(callable);
    return decorateFuture(runnableFuture, getCandidatePriority(callable));
  }

  @Override protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
    RunnableFuture<T> runnableFuture = super.newTaskFor(runnable, value);
    return decorateFuture(runnableFuture, getCandidatePriority(runnable));
  }

  private int getCandidatePriority(Object callable) {
    return callable instanceof PriorizableInteractor
        ? ((PriorizableInteractor) callable).getPriority() : 0;
  }

  private <T> RunnableFuture<T> decorateFuture(RunnableFuture<T> runnableFuture, int priority) {
    return new PriorityRunnableFutureDecorated<>(runnableFuture, priority);
  }
}
