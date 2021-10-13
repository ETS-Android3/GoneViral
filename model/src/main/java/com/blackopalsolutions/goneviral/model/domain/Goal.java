package com.blackopalsolutions.goneviral.model.domain;

public class Goal {
  private int goalId;
  private String condition;

  /**
   * Creates an empty goal object.
   */
  public Goal() {}

  /**
   * Creates a goal object.
   * @param goalId the id for the goal.
   * @param condition the condition for winning the game.
   */
  public Goal(int goalId, String condition) {
    this.goalId = goalId;
    this.condition = condition;
  }

  public int getGoalId() {
    return goalId;
  }

  public String getCondition() {
    return condition;
  }

  public void setGoalId(int goalId) {
    this.goalId = goalId;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }
}
