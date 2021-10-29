package com.blackopalsolutions.goneviral.model.request

import com.blackopalsolutions.goneviral.model.domain.User

class UpdateUserRequest(val user: User) : Request() {

    override fun encodeToJsonString(): String =
        "{user:{" +
                "\"id\":${user.id}," +
                "\"username\":${user.username}," +
                "\"password\":${user.password}," +
                "\"gamesWon\":${user.gamesWon}," +
                "\"gamesLost\":${user.gamesLost}}}"
}