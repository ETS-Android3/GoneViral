package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.GoalDao;
import com.blackopalsolutions.goneviral.backend.model.domain.Goal;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
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
    public GetGoalResponse getGoal(IdRequest request) {
        try {
            Goal goal = dao.getGoal(request.getId());
            return new GetGoalResponse(goal);
        } catch (DatabaseAccessException e) {
            return new GetGoalResponse(e.getMessage());
        }
    }

    /**
     * Removes a goal from the database.
     * @param request the request to remove the goal.
     * @return whether or not it was a success.
     */
    public Response removeGoal(IdRequest request) {
        try {
            dao.removeGoal(request.getId());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
    }
}
