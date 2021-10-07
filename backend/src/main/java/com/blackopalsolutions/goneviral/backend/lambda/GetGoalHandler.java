package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.GetGoalRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetGoalResponse;
import com.blackopalsolutions.goneviral.backend.service.GoalService;

public class GetGoalHandler implements RequestHandler<GetGoalRequest, GetGoalResponse> {

    /**
     * Handles a getGoal request.
     * @param request the request to retrieve a goal.
     * @param context the context of the request.
     * @return the goal retrieved, or a failure message.
     */
    @Override
    public GetGoalResponse handleRequest(GetGoalRequest request, Context context) {
        return (new GoalService()).getGoal(request);
    }
}
