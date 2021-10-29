package com.blackopalsolutions.goneviral.android.util

import com.blackopalsolutions.goneviral.model.domain.Card
import com.blackopalsolutions.goneviral.model.domain.User
import org.json.JSONObject
import org.json.JSONStringer

class JsonUtility {
    fun encodeUser(user: User): String {
        return JSONStringer()
            .`object`()
                .key("id")
                .value(user.id)
                .key("username")
                .value(user.username)
                .key("password")
                .value(user.password)
                .key("gamesWon")
                .value(user.gamesWon)
                .key("gamesLost")
                .value(user.gamesLost)
            .endObject()
            .toString()
    }

    fun decodeUser(json: String): User {
        val obj = JSONObject(json)
        val id = obj.getInt("id")
        val username = obj.getString("username")
        val password = obj.getString("password")
        val gamesWon = obj.getInt("gamesWon")
        val gamesLost = obj.getInt("gamesLost")
        return User(id, username, password, gamesWon, gamesLost)
    }

    fun encodeCard(card: Card): String {
        return JSONStringer()
            .`object`()
                .key("cardId")
                .value(card.cardId)
                .key("type")
                .value(card.type)
                .key("cost")
                .value(card.cost)
                .key("description")
                .value(card.description)
                .key("effect")
                .value(card.effect)
                .key("title")
                .value(card.title)
                .key("value")
                .value(card.value)
                .key("backTexture")
                .value(card.backTexture)
                .key("frontTexture")
                .value(card.frontTexture)
            .endObject()
            .toString()
    }

    fun decodeCard(json: String): Card {
        val obj = JSONObject(json)
        val cardId = obj.getInt("cardId")
        val type = obj.getString("type")
        val cost = obj.getInt("cost")
        val description = obj.getString("description")
        val effect = obj.getString("effect")
        val title = obj.getString("title")
        val value = obj.getInt("value")
        val backTexture = obj.getString("backTexture")
        val frontTexture = obj.getString("frontTexture")
        return Card(cardId, type, cost, description, effect, title, value, backTexture, frontTexture)
    }

}