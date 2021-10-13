package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.UserService;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.Response;

public class RemoveUserHandler implements RequestHandler<IdRequest, Response> {

    /**
     * Handle a removeUser request.
     * @param request the request for removal.
     * @param context the request context.
     * @return whether or not it was successful.
     */
    @Override
    public Response handleRequest(IdRequest request, Context context) {
        return (new UserService()).removeUser(request);
    }
}
