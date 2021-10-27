package com.blackopalsolutions.goneviral.model.domain

class Card(val cardId: Int, val type: String, val cost: Int, val description: String,
           val effect: String, val title: String, val value: Int,
           val backTexture: String, val frontTexture: String) {
}