package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.GetGoalRequest;
import com.blackopalsolutions.goneviral.backend.model.response.Response;
import com.blackopalsolutions.goneviral.backend.service.GoalService;

public class GetGoalHandler implements RequestHandler<GetGoalRequest, Response> {

    @Override
    public Response handleRequest(GetGoalRequest request, Context context) {
        return (new GoalService()).getGoal(request);
    }
}
