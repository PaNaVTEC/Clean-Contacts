package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.Collections;
import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.InteractorError;
import me.panavtec.cleancontacts.domain.interactors.contacts.Chain.TypeChecker;

class ListTypeChecker<T> implements TypeChecker<List<T>> {

  private final InteractorError error;

  ListTypeChecker(InteractorError error) {
    this.error = error;
  }

  @Override public boolean isValid(List<T> object) {
    return !object.isEmpty();
  }

  @Override public List<T> empty() {
    return Collections.emptyList();
  }

  @Override public InteractorError error() {
    return error;
  }
}
