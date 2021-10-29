package com.blackopalsolutions.goneviral.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blackopalsolutions.goneviral.backend.service.CardService;
import com.blackopalsolutions.goneviral.model.request.PageRequest;
import com.blackopalsolutions.goneviral.model.response.GetCardsResponse;

public class GetCardsHandler implements RequestHandler<PageRequest, GetCardsResponse> {

    /**
     * Handles a getCards request.
     * @param request the request to retrieve a card.
     * @param context the request context.
     * @return the cards retrieved, or a failure message.
     */
    @Override
    public GetCardsResponse handleRequest(PageRequest request, Context context) {
        return (new CardService()).getCards(request);
    }
}
