package com.blackopalsolutions.goneviral.android.model.response;

import com.blackopalsolutions.goneviral.android.model.domain.Goal;

public class GetGoalResponse extends Response {

    private final Goal goal;

    public GetGoalResponse(Goal goal) {
        super(goal != null);
        this.goal = goal;
    }

    public GetGoalResponse(String message) {
        super(message);
        this.goal = null;
    }

    public Goal getGoal() {
        return goal;
    }
}
