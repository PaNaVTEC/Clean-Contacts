package me.panavtec.cleancontacts.domain.invoker;

public enum InteractorPriorityImp {
  LOW(1),
  MEDIUM(50),
  HIGH(100),
  URGENT(200);

  private int priority;

  InteractorPriorityImp(int priority) {
    this.priority = priority;
  }

  public int getPriorityValue() {
    return priority;
  }
}
