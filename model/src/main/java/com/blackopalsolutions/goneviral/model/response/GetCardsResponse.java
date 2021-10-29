package com.blackopalsolutions.goneviral.model.response;

import com.blackopalsolutions.goneviral.model.domain.Card;

import java.util.List;

public class GetCardsResponse extends Response {
    private final List<Card> cards;

    public GetCardsResponse(List<Card> cards) {
        super(true);
        this.cards = cards;
    }

    public GetCardsResponse(String message) {
        super(message);
        this.cards = null;
    }

    public List<Card> getCards() {
        return cards;
    }
}
