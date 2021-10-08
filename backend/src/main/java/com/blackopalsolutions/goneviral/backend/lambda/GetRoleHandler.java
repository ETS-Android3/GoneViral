package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetRoleResponse;
import com.blackopalsolutions.goneviral.backend.service.RoleService;

public class GetRoleHandler implements RequestHandler<IdRequest, GetRoleResponse> {

    /**
     * Handles a getRole request.
     * @param request the request for role retrieval.
     * @param context the context of the role.
     * @return whether or not it was a success.
     */
    @Override
    public GetRoleResponse handleRequest(IdRequest request, Context context) {
        return (new RoleService()).getRole(request);
    }
}
