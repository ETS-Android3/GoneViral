package com.blackopalsolutions.goneviral.model;

public class Goal {
  private final String goalId;
  private final String roleId;
  private final String condition;

  /**
   * Creates a goal object.
   * @param goalId the id for the goal.
   * @param roleId the role id associated with the goal.
   * @param condition the condition for winning the game.
   */
  public Goal(String goalId, String roleId, String condition) {
    this.goalId = goalId;
    this.roleId = roleId;
    this.condition = condition;
  }

  public String getGoalId() {
    return goalId;
  }

  public String getRoleId() {
    return roleId;
  }

  public String getCondition() {
    return condition;
  }
}
