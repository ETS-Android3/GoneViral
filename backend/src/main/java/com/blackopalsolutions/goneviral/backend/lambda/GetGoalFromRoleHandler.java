package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.GoalService;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetGoalResponse;

public class GetGoalFromRoleHandler implements RequestHandler<IdRequest, GetGoalResponse> {

    /**
     * Handles a getGoalFromRole request.
     * @param request the request to retrieve a goal.
     * @param context the request context.
     * @return whether or not it was a success.
     */
    @Override
    public GetGoalResponse handleRequest(IdRequest request, Context context) {
        return (new GoalService()).getGoalFromRole(request);
    }
}
