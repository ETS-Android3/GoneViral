package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetGoalResponse;
import com.blackopalsolutions.goneviral.backend.service.GoalService;

public class GetGoalHandler implements RequestHandler<IdRequest, GetGoalResponse> {

    /**
     * Handles a getGoal request.
     * @param request the request to retrieve a goal.
     * @param context the context of the request.
     * @return the goal retrieved, or a failure message.
     */
    @Override
    public GetGoalResponse handleRequest(IdRequest request, Context context) {
        return (new GoalService()).getGoal(request);
    }
}
