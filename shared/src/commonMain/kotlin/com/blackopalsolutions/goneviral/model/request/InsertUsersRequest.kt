package com.blackopalsolutions.goneviral.model.request

import com.blackopalsolutions.goneviral.model.domain.User

class InsertUsersRequest(val users: List<User>): Request() {

    override fun encodeToJsonString(): String {
        var json = "{users:["
        for (i in users.indices) {
            if (i != 0) {
                json += ","
            }
            json += encodeUserToJsonString(users[i])
        }
        json += "]}"
        return json
    }

    private fun encodeUserToJsonString(user: User): String =
        "{\"id\":${user.id}," +
            "\"username\":${user.username}," +
            "\"password\":${user.password}," +
            "\"gamesWon\":${user.gamesWon}," +
            "\"gamesLost\":${user.gamesLost}}"
}