package me.panavtec.presentation.common;

public class DecoratedInteractorOutput<T, E extends Exception> implements InteractorOutput<T, E> {

  private final Action<T> action;
  private final Action<E> actionError;
  private final Action<Void> actionCancel;

  private DecoratedInteractorOutput(Action<T> action, Action<E> actionError,
      Action<Void> actionCancel) {
    this.action = action;
    this.actionError = actionError;
    this.actionCancel = actionCancel;
  }

  @Override public void onResult(final T result) {
    action.onAction(result);
  }

  @Override public void onError(E e) {
    if (actionError != null) {
      actionError.onAction(e);
    }
  }

  @Override public void onCancel() {
    if (actionCancel != null) {
      actionCancel.onAction(null);
    }
  }

  public static class Builder<T, E extends Exception> {

    private Action<T> action;
    private Action<E> actionError;
    private Action<Void> actionCancel;

    public Builder() {
    }

    public Builder<T, E> onResult(Action<T> action) {
      this.action = action;
      return this;
    }

    public Builder<T, E> onError(Action<E> actionError) {
      this.actionError = actionError;
      return this;
    }

    public Builder<T, E> onCancel(Action<Void> actionCancel) {
      this.actionCancel = actionCancel;
      return this;
    }

    public DecoratedInteractorOutput<T, E> build() {
      if (action == null) {
        throw new RuntimeException("Action cannot be null");
      }

      return new DecoratedInteractorOutput<>(action, actionError, actionCancel);
    }
  }
}
