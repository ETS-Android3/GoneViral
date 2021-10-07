package com.blackopalsolutions.goneviral.backend.model.request;

import com.blackopalsolutions.goneviral.backend.model.domain.Goal;

public class UpdateGoalRequest {
    private Goal goal;

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Goal getGoal() {
        return goal;
    }
}
