package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.CardDao;
import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.model.domain.Card;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
import com.blackopalsolutions.goneviral.backend.model.request.InsertCardsRequest;
import com.blackopalsolutions.goneviral.backend.model.request.UpdateCardRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetCardResponse;
import com.blackopalsolutions.goneviral.backend.model.response.Response;

public class CardService {
    private final CardDao dao = getCardDao();

    CardDao getCardDao() {
        return new CardDao();
    }

    /**
     * Inserts a series of cards into the database.
     * @param request the request for insertion.
     * @return whether or not it was successful.
     */
    public Response insertCards(InsertCardsRequest request) {
        try {
            dao.insertCards(request.getCards());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
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
     * Updates a card in the database.
     * @param request the request to update the card.
     * @return whether or not it was successful.
     */
    public Response updateCard(UpdateCardRequest request) {
        try {
            dao.updateCard(request.getCard());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
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
