package me.panavtec.cleancontacts.domain.interactors;

public enum InteractorPriority {
  LOW(1),
  MEDIUM(50),
  HIGH(100),
  URGENT(200);

  private int priority;

  InteractorPriority(int priority) {
    this.priority = priority;
  }

  public int getPriorityValue() {
    return priority;
  }
}
