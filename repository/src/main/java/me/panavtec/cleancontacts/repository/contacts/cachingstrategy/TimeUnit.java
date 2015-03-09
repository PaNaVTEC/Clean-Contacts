package me.panavtec.cleancontacts.repository.contacts.cachingstrategy;

public enum TimeUnit {
  MILLI_SECONDS(1),
  SECONDS(1000),
  MINUTES(60 * 1000),
  HOURS(60 * 60 * 1000);

  private int toMillis;

  TimeUnit(int toMillis) {
    this.toMillis = toMillis;
  }

  public int toMillis() {
    return toMillis;
  }
}
