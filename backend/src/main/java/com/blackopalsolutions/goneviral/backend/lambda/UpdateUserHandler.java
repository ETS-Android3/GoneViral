package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.UserService;
import com.blackopalsolutions.goneviral.model.request.UpdateUserRequest;
import com.blackopalsolutions.goneviral.model.response.Response;

public class UpdateUserHandler implements RequestHandler<UpdateUserRequest, Response> {

    /**
     * Handle an updateUser request.
     * @param request the request to update a user.
     * @param context the request context.
     * @return whether or not it was a success.
     */
    @Override
    public Response handleRequest(UpdateUserRequest request, Context context) {
        return (new UserService()).updateUser(request);
    }
}
