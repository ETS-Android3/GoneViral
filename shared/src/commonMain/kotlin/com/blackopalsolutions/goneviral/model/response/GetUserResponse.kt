package com.blackopalsolutions.goneviral.model.response

import com.blackopalsolutions.goneviral.model.domain.User

class GetUserResponse : Response {
    val user: User?

    constructor(user: User) : super(true) {
        this.user = user
    }

    constructor(message: String) : super(message) {
        user = null
    }
}