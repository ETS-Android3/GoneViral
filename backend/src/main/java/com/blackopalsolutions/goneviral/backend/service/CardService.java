package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.CardDao;
import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.model.domain.Card;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetCardResponse;
import com.blackopalsolutions.goneviral.backend.model.response.Response;

public class CardService {
    private final CardDao dao = getCardDao();

    CardDao getCardDao() {
        return new CardDao();
    }

    /**
     * Retrieves a card from the database.
     * @param request the request to retrieve the card.
     * @return the card retrieved, or a failure message.
     */
    public GetCardResponse getCard(IdRequest request) {
        try {
            Card card = dao.getCard(request.getId());
            return new GetCardResponse(card);
        } catch (DatabaseAccessException e) {
            return new GetCardResponse(e.getMessage());
        }
    }

    /**
     * Removes a card from the database.
     * @param request the request to remove the card.
     * @return whether or not it was a success.
     */
    public Response removeCard(IdRequest request) {
        try {
            dao.removeCard(request.getId());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
    }
}
