package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.RoleService;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetRoleResponse;

public class GetRoleFromGoalHandler implements RequestHandler<IdRequest, GetRoleResponse> {

    /**
     * Handles a getRoleFromGoal request.
     * @param request the request to retrieve a role.
     * @param context the request context.
     * @return whether or not it was a success.
     */
    @Override
    public GetRoleResponse handleRequest(IdRequest request, Context context) {
        return (new RoleService()).getRoleFromGoal(request);
    }
}
