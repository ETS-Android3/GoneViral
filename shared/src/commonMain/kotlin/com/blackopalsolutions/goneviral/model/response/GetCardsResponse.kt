package com.blackopalsolutions.goneviral.model.response

import com.blackopalsolutions.goneviral.model.domain.Card

class GetCardsResponse : Response {
    val cards: List<Card>?

    constructor(cards: List<Card>) : super(true) {
        this.cards = cards
    }

    constructor(message: String) : super(message) {
        this.cards = null
    }
}