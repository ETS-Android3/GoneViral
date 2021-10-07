package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.GoalDao;
import com.blackopalsolutions.goneviral.backend.model.domain.Goal;
import com.blackopalsolutions.goneviral.backend.model.request.GetGoalRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetGoalResponse;
import com.blackopalsolutions.goneviral.backend.model.response.Response;

public class GoalService {
    private final GoalDao dao = getGoalDao();

    GoalDao getGoalDao() {
        return new GoalDao();
    }

    /**
     * Retrieves a goal from the database.
     * @param request the request for the retrieval.
     * @return whether or not the retrieval was a success.
     */
    public GetGoalResponse getGoal(GetGoalRequest request) {
        try {
            Goal goal = dao.getGoal(request.getGoalId());
            return new GetGoalResponse(goal);
        } catch (DatabaseAccessException e) {
            return new GetGoalResponse(e.getMessage());
        }
    }
}
