package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.InsertGoalsRequest;
import com.blackopalsolutions.goneviral.backend.model.response.Response;
import com.blackopalsolutions.goneviral.backend.service.GoalService;

public class InsertGoalHandler implements RequestHandler<InsertGoalsRequest, Response> {

    /**
     * Handles request to insert a series of goals.
     * @param request the request for insertion.
     * @param context the request context.
     * @return whether or not it was successful
     */
    @Override
    public Response handleRequest(InsertGoalsRequest request, Context context) {
        return (new GoalService()).insertGoals(request);
    }
}
