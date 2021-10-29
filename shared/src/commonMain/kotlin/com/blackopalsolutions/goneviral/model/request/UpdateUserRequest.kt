package com.blackopalsolutions.goneviral.model.request

import com.blackopalsolutions.goneviral.model.domain.User
import kotlinx.serialization.Serializable

@Serializable
class UpdateUserRequest(val user: User) {
}