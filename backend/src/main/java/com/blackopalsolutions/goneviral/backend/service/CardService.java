package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.CardDao;
import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.model.domain.Card;
import com.blackopalsolutions.goneviral.backend.model.request.GetCardRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetCardResponse;

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
    public GetCardResponse getCard(GetCardRequest request) {
        try {
            Card card = dao.getCard(request.getCardId());
            return new GetCardResponse(card);
        } catch (DatabaseAccessException e) {
            return new GetCardResponse(e.getMessage());
        }
    }
}
