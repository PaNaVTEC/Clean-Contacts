package me.panavtec.presentation.common;

import java.util.concurrent.ExecutionException;

public class DecoratedInteractorOutput<T> implements InteractorOutput<T> {

  private final Action<T> action;
  private final Action<ExecutionException> actionError;
  private final Action<Void> actionCancel;

  private DecoratedInteractorOutput(Action<T> action, Action<ExecutionException> actionError,
      Action<Void> actionCancel) {
    this.action = action;
    this.actionError = actionError;
    this.actionCancel = actionCancel;
  }

  @Override public void onResult(final T result) {
    action.onAction(result);
  }

  @Override public void onError(ExecutionException e) {
    if (actionError != null) {
      actionError.onAction(e);
    }
  }

  @Override public void onCancel() {
    if (actionCancel != null) {
      actionCancel.onAction(null);
    }
  }

  public static class Builder<T> {

    private Action<T> action;
    private Action<ExecutionException> actionError;
    private Action<Void> actionCancel;

    public Builder() {
    }

    public Builder<T> onResult(Action<T> action) {
      this.action = action;
      return this;
    }

    public Builder<T> onError(Action<ExecutionException> actionError) {
      this.actionError = actionError;
      return this;
    }

    public Builder<T> onCancel(Action<Void> actionCancel) {
      this.actionCancel = actionCancel;
      return this;
    }

    public DecoratedInteractorOutput<T> build() {
      if (action == null) {
        throw new RuntimeException("Action cannot be null");
      }

      return new DecoratedInteractorOutput<>(action, actionError, actionCancel);
    }
  }
}
