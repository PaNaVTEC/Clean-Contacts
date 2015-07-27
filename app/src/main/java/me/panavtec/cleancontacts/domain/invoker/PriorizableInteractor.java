package me.panavtec.cleancontacts.domain.invoker;

public interface PriorizableInteractor {
  int getPriority();
  String getDescription();
}
