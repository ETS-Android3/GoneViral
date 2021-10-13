package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.UserService;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetUserResponse;

public class GetUserHandler implements RequestHandler<IdRequest, GetUserResponse> {

    /**
     * Handle a getUser request.
     * @param request the request for retrieval.
     * @param context the request context.
     * @return whether or not it was a success.
     */
    @Override
    public GetUserResponse handleRequest(IdRequest request, Context context) {
        return (new UserService()).getUser(request);
    }
}
