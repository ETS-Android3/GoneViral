package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.model.request.GetCardRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetCardResponse;
import com.blackopalsolutions.goneviral.backend.service.CardService;

public class GetCardHandler implements RequestHandler<GetCardRequest, GetCardResponse> {

    /**
     * Handles a getCard request.
     * @param request the request to retrieve a card.
     * @param context the request context.
     * @return the card retrieved, or a failure message.
     */
    @Override
    public GetCardResponse handleRequest(GetCardRequest request, Context context) {
        return (new CardService()).getCard(request);
    }
}
