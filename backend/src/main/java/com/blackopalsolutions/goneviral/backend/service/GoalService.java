package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.GoalDao;
import com.blackopalsolutions.goneviral.backend.model.domain.Goal;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
import com.blackopalsolutions.goneviral.backend.model.request.InsertGoalsRequest;
import com.blackopalsolutions.goneviral.backend.model.request.UpdateGoalRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetGoalResponse;
import com.blackopalsolutions.goneviral.backend.model.response.Response;

public class GoalService {
    private final GoalDao dao = getGoalDao();

    GoalDao getGoalDao() {
        return new GoalDao();
    }

    /**
     * Inserts a series of goals into the database.
     * @param request the request for insertion.
     * @return whether or not it was successful.
     */
    public Response insertGoals(InsertGoalsRequest request) {
        try {
            dao.insertGoals(request.getGoals());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
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
     * Updates a goal in the database.
     * @param request the request to update a goal.
     * @return whether or not it was a success.
     */
    public Response updateGoal(UpdateGoalRequest request) {
        try {
            dao.updateGoal(request.getGoal());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
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
