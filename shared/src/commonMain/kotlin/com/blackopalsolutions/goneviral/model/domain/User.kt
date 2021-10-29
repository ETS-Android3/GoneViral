package com.blackopalsolutions.goneviral.model.domain
import kotlinx.serialization.Serializable

@Serializable
class User(val id: Int, val username: String, val password: String,
           val gamesWon: Int, val gamesLost: Int) {
}