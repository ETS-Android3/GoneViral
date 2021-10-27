package com.blackopalsolutions.goneviral.model.response

import com.blackopalsolutions.goneviral.model.domain.Role

class GetRoleResponse : Response {
    val role: Role?

    constructor(role: Role) : super(true) {
        this.role = role
    }

    constructor(message: String) : super(message) {
        role = null
    }
}