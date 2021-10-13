package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.GoalDao;
import com.blackopalsolutions.goneviral.model.domain.Goal;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetGoalResponse;

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
    public GetGoalResponse getGoal(IdRequest request) {
        try {
            Goal goal = dao.getGoal(request.getId());
            return new GetGoalResponse(goal);
        } catch (DatabaseAccessException e) {
            return new GetGoalResponse(e.getMessage());
        }
    }
}
