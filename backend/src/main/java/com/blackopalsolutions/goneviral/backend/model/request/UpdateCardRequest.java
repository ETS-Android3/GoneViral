package com.blackopalsolutions.goneviral.backend.model.request;

import com.blackopalsolutions.goneviral.backend.model.domain.Card;

public class UpdateCardRequest {
    private Card card;

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
