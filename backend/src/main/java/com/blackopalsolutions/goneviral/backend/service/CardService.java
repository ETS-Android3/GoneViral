package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.CardDao;
import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.model.domain.Card;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.request.PageRequest;
import com.blackopalsolutions.goneviral.model.response.GetCardResponse;
import com.blackopalsolutions.goneviral.model.response.GetCardsResponse;

import java.util.List;

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
     * Retrieves all the cards from the database
     * @param request the request for which page to retrieve (currently not implemented)
     * @return the cards retrieved, or a failure message.
     */
    public GetCardsResponse getCards(PageRequest request) {
        try {
            List<Card> cards = dao.getCards();
            return new GetCardsResponse(cards);
        } catch (DatabaseAccessException e) {
            return new GetCardsResponse(e.getMessage());
        }
    }
}
