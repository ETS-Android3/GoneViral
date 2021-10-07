package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
import com.blackopalsolutions.goneviral.backend.model.response.Response;
import com.blackopalsolutions.goneviral.backend.service.GoalService;

public class RemoveGoalHandler implements RequestHandler<IdRequest, Response> {

    /**
     * Removes a goal from the database.
     * @param request the request to remove a goal.
     * @param context the context of the request.
     * @return whether or not it was a success.
     */
    @Override
    public Response handleRequest(IdRequest request, Context context) {
        return (new GoalService()).removeGoal(request);
    }
}
