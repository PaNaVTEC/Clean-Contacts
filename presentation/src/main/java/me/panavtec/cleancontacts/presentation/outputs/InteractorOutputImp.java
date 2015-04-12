package me.panavtec.cleancontacts.presentation.outputs;

import me.panavtec.cleancontacts.presentation.outputs.interactors.InteractorOutput;

public class InteractorOutputImp<T, E extends Exception> implements InteractorOutput<T,E> {

  private final Action<T> action;
  private final Action<E> actionError;
  private final ThreadSpec actionThread;
  private final ThreadSpec actionErrorThread;

  private InteractorOutputImp(Action<T> action, Action<E> actionError, ThreadSpec actionThread,
      ThreadSpec actionErrorThread) {
    this.action = action;
    this.actionError = actionError;
    this.actionThread = actionThread;
    this.actionErrorThread = actionErrorThread;
  }

  @Override public void onResult(final T result) {
    actionThread.execute(action, result);
  }

  @Override public void onError(final E error) {
    actionErrorThread.execute(actionError, error);
  }

  public static class Builder<T, E extends Exception> {

    private Action<T> action;
    private ThreadSpec actionThread;
    private Action<E> actionError;
    private ThreadSpec actionErrorThread;

    public Builder() {
    }

    public Builder(ThreadSpec threadSpec) {
      this.actionThread = threadSpec;
      this.actionErrorThread = threadSpec;
    }

    public Builder<T, E> onResult(Action<T> action) {
      this.action = action;
      return this;
    }

    public Builder<T, E> onError(Action<E> actionError) {
      this.actionError = actionError;
      return this;
    }

    public Builder<T, E> onResult(Action<T> action, ThreadSpec actionThread) {
      this.action = action;
      this.actionThread = actionThread;
      return this;
    }

    public Builder<T, E> onError(Action<E> actionError, ThreadSpec actionErrorThread) {
      this.actionError = actionError;
      this.actionErrorThread = actionErrorThread;
      return this;
    }

    public InteractorOutputImp<T, E> build() {
      if (action == null) {
        throw new RuntimeException("Action cannot be null");
      }

      if (actionThread == null) {
        throw new RuntimeException("Action Thread cannot be null");
      }

      if (actionError != null && actionErrorThread == null) {
        throw new RuntimeException(
            "Action error thread cannot be null if you have configurated an action error");
      }

      return new InteractorOutputImp<>(action, actionError, actionThread, actionErrorThread);
    }
  }
}
