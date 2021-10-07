package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.InsertCardsRequest;
import com.blackopalsolutions.goneviral.backend.model.response.Response;
import com.blackopalsolutions.goneviral.backend.service.CardService;

public class InsertCardHandler implements RequestHandler<InsertCardsRequest, Response> {

    /**
     * Handles request to insert a series of cards.
     * @param request the request for insertion.
     * @param context the request context.
     * @return whether or not it was a success.
     */
    @Override
    public Response handleRequest(InsertCardsRequest request, Context context) {
        return (new CardService()).insertCards(request);
    }
}
