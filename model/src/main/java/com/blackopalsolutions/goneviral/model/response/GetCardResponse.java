package com.blackopalsolutions.goneviral.model.response;

import com.blackopalsolutions.goneviral.model.domain.Card;

public class GetCardResponse extends Response {
    private final Card card;

    public GetCardResponse(Card card) {
        super(card != null);
        this.card = card;
    }

    public GetCardResponse(String message) {
        super(message);
        this.card = null;
    }

    public Card getCard() {
        return card;
    }
}
