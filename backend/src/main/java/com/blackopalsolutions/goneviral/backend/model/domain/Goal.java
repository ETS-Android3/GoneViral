package com.blackopalsolutions.goneviral.backend.model.domain;

public class Goal {
  private int goalId;
  private int roleId;
  private String condition;

  public Goal() {}

  /**
   * Creates a goal object.
   * @param goalId the id for the goal.
   * @param roleId the role id associated with the goal.
   * @param condition the condition for winning the game.
   */
  public Goal(int goalId, int roleId, String condition) {
    this.goalId = goalId;
    this.roleId = roleId;
    this.condition = condition;
  }

  public int getGoalId() {
    return goalId;
  }

  public int getRoleId() {
    return roleId;
  }

  public String getCondition() {
    return condition;
  }

  public void setGoalId(int goalId) {
    this.goalId = goalId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }
}
