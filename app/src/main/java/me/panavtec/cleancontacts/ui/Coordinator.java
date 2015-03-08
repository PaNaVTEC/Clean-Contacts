package me.panavtec.cleancontacts.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Coordinator {

  private List<String> actions;
  private Set<String> completedActions = new TreeSet<>();
  private CoordinatorCompleteAction coordinatorCompleteAction;

  public Coordinator(CoordinatorCompleteAction coordinatorCompleteAction, String... actions) {
    if (coordinatorCompleteAction == null) {
      throw new NullPointerException("Coordinate complete action must not be null");
    }
    if (actions == null || actions.length == 0) {
      throw new IllegalArgumentException("There are no actions to coordinate");
    }
    this.coordinatorCompleteAction = coordinatorCompleteAction;
    this.actions = Arrays.asList(actions);
  }

  public void addAction(String action) {
    actions.add(action);
  }

  public void completeAction(String action) {
    completedActions.add(action);
    checkComplete();
  }

  public void reset() {
    completedActions.clear();
  }

  private void checkComplete() {
    if (actions.size() == completedActions.size()) {
      coordinatorCompleteAction.onCoordinatorComplete();
    }
  }

  public static interface CoordinatorCompleteAction {
    public void onCoordinatorComplete();
  }
}
