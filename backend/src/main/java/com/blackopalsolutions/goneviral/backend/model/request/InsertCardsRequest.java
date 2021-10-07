package com.blackopalsolutions.goneviral.backend.model.request;

import com.blackopalsolutions.goneviral.backend.model.domain.Card;

import java.util.List;

public class InsertCardsRequest {
    private List<Card> cards;

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card[] getCards() {
        if (cards == null) {
            return new Card[0];
        }
        return cards.toArray(new Card[0]);
    }
}
