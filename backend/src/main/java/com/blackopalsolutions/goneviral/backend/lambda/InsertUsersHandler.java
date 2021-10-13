package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.UserService;
import com.blackopalsolutions.goneviral.model.request.InsertUsersRequest;
import com.blackopalsolutions.goneviral.model.response.Response;

public class InsertUsersHandler implements RequestHandler<InsertUsersRequest, Response> {

    /**
     * Handle an insertUsers request.
     * @param request the request for insertion.
     * @param context the request context.
     * @return whether or not it was successful.
     */
    @Override
    public Response handleRequest(InsertUsersRequest request, Context context) {
        return (new UserService()).insertUsers(request);
    }
}
