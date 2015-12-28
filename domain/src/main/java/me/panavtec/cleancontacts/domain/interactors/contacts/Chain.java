package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.InteractorError;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;

class Chain<T> {

  private final TypeChecker<T> typeChecker;
  private final List<? extends TypeProvider<T>> providers;
  private final List<? extends TypeStorer<T>> storers;

  private Chain(Builder<T> builder) {
    this.typeChecker = builder.typeChecker;
    this.providers = builder.providers;
    this.storers = builder.storers;
  }

  public InteractorResponse<T> obtain() {
    return recursiveObtain(0);
  }

  private InteractorResponse<T> recursiveObtain(int nextProvider) {
    boolean isLast = nextProvider == providers.size() - 1;
    TypeProvider<T> provider = providers.get(nextProvider);
    try {
      T result = provider.obtain();
      if (typeChecker.isValid(result)) {
        for (int i = 0; i < nextProvider; i++) {
          storers.get(i).store(result);
        }
        return new InteractorResponse<>(result);
      } else if (isLast) {
        return new InteractorResponse<>(typeChecker.empty());
      }
    } catch (RuntimeException e) {
      if (isLast) {
        return new InteractorResponse<>(typeChecker.error());
      }
    }
    return recursiveObtain(++nextProvider);
  }

  interface TypeProvider<T> {
    T obtain();
  }

  interface TypeStorer<T> {
    void store(T t);
  }

  interface TypeChecker<T> {
    boolean isValid(T object);

    T empty();

    InteractorError error();
  }

  public static class Builder<T> {
    private TypeChecker<T> typeChecker;
    private List<? extends TypeProvider<T>> providers;
    private List<? extends TypeStorer<T>> storers;

    public Builder<T> typeChecker(TypeChecker<T> typeChecker) {
      this.typeChecker = typeChecker;
      return this;
    }

    public Builder<T> providers(List<? extends TypeProvider<T>> providers) {
      this.providers = providers;
      return this;
    }

    public Builder<T> storers(List<? extends TypeStorer<T>> storers) {
      this.storers = storers;
      return this;
    }

    public Chain<T> build() {
      return new Chain<>(this);
    }
  }
}
