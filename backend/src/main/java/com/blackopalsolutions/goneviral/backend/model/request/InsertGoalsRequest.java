package com.blackopalsolutions.goneviral.backend.model.request;

import com.blackopalsolutions.goneviral.backend.model.domain.Goal;

public class InsertGoalsRequest {
    private Goal[] goals;

    public void setGoals(Goal[] goals) {
        this.goals = goals;
    }

    public Goal[] getGoals() {
        return goals;
    }
}
