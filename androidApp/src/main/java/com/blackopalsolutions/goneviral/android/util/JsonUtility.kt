package com.blackopalsolutions.goneviral.android.util

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
}