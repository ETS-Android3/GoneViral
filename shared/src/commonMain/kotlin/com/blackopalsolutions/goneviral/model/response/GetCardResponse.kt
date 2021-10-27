package com.blackopalsolutions.goneviral.model.response

import com.blackopalsolutions.goneviral.model.domain.Card

class GetCardResponse : Response {
    val card: Card?

    constructor(card: Card) : super(true) {
        this.card = card
    }

    constructor(message: String) : super(message) {
        card = null
    }
}