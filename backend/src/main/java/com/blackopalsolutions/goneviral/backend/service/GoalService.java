package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.GoalDao;
import com.blackopalsolutions.goneviral.backend.dao.RoleGoalDao;
import com.blackopalsolutions.goneviral.model.domain.Goal;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetGoalResponse;

public class GoalService {
    private final GoalDao goalDao = getGoalDao();
    private final RoleGoalDao rgDao = getRoleGoalDao();

    GoalDao getGoalDao() {
        return new GoalDao();
    }

    RoleGoalDao getRoleGoalDao() {
        return new RoleGoalDao();
    }

    /**
     * Retrieves a goal from the database.
     * @param request the request for the retrieval.
     * @return whether or not the retrieval was a success.
     */
    public GetGoalResponse getGoal(IdRequest request) {
        try {
            Goal goal = goalDao.getGoal(request.getId());
            return new GetGoalResponse(goal);
        } catch (DatabaseAccessException e) {
            return new GetGoalResponse(e.getMessage());
        }
    }

    /**
     * Retrieves a goal from the database, given a role id.
     * @param request the request for the retrieval.
     * @return whether or not the retrieval was a success.
     */
    public GetGoalResponse getGoalFromRole(IdRequest request) {
        try {
            Goal goal = rgDao.getGoalFromRole(request.getId());
            return new GetGoalResponse(goal);
        } catch (DatabaseAccessException e) {
            return new GetGoalResponse(e.getMessage());
        }
    }
}
