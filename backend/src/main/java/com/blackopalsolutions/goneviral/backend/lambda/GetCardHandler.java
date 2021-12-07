package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.model.request.StringRequest;
import com.blackopalsolutions.goneviral.model.response.GetCardResponse;
import com.blackopalsolutions.goneviral.backend.service.CardService;

public class GetCardHandler implements RequestHandler<StringRequest, GetCardResponse> {

    /**
     * Handles a getCard request.
     * @param request the request to retrieve a card.
     * @param context the request context.
     * @return the card retrieved, or a failure message.
     */
    @Override
    public GetCardResponse handleRequest(StringRequest request, Context context) {
        return (new CardService()).getCard(request);
    }
}
