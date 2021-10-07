package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.GoalDao;
import com.blackopalsolutions.goneviral.backend.model.domain.Goal;
import com.blackopalsolutions.goneviral.backend.model.request.GetGoalRequest;
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
    public Response getGoal(GetGoalRequest request) {
        try {
            Goal goal = dao.getGoal(request.getGoalId());
            if (goal == null) {
                return new Response(false);
            }
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
    }
}
