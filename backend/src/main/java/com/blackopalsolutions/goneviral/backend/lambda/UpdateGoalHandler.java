package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.UpdateGoalRequest;
import com.blackopalsolutions.goneviral.backend.model.response.Response;
import com.blackopalsolutions.goneviral.backend.service.GoalService;

public class UpdateGoalHandler implements RequestHandler<UpdateGoalRequest, Response> {

    /**
     * Handles a request to update a goal.
     * @param request the request to update a goal.
     * @param context the request context.
     * @return whether or not it was a success.
     */
    @Override
    public Response handleRequest(UpdateGoalRequest request, Context context) {
        return (new GoalService()).updateGoal(request);
    }
}
