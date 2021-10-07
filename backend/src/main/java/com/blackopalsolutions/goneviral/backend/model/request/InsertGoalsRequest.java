package com.blackopalsolutions.goneviral.backend.model.request;

import com.blackopalsolutions.goneviral.backend.model.domain.Goal;

import java.util.List;

public class InsertGoalsRequest {
    private List<Goal> goals;

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public Goal[] getGoals() {
        if (goals == null) {
            return new Goal[0];
        }
        return goals.toArray(new Goal[0]);
    }
}
