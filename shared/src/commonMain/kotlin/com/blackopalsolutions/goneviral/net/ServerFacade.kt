package com.blackopalsolutions.goneviral.net

import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.model.request.InsertUsersRequest
import com.blackopalsolutions.goneviral.model.request.UpdateUserRequest
import com.blackopalsolutions.goneviral.model.response.*
import kotlinx.serialization.ExperimentalSerializationApi

class ServerFacade {
    private val serverUrl = "https://cnatx8j1tg.execute-api.us-east-2.amazonaws.com/dev"

    @PublishedApi
    internal val communicator = ClientCommunicator(serverUrl)

    @ExperimentalSerializationApi
    fun getCard(request: IdRequest): GetCardResponse = post(request, "/getcard")

    @ExperimentalSerializationApi
    fun getGoal(request: IdRequest): GetGoalResponse = post(request, "/getgoal")

    @ExperimentalSerializationApi
    fun getGoalFromRole(request: IdRequest): GetGoalResponse
        = post(request, "/getgoalfromrole")

    @ExperimentalSerializationApi
    fun getRole(request: IdRequest): GetRoleResponse = post(request, "/getrole")

    @ExperimentalSerializationApi
    fun getRoleFromGoal(request: IdRequest): GetRoleResponse
        = post(request, "/getrolefromgoal")

    @ExperimentalSerializationApi
    fun getUser(request: IdRequest): GetUserResponse = post(request, "/getuser")

    @ExperimentalSerializationApi
    fun insertUsers(request: InsertUsersRequest): Response = post(request, "/insertusers")

    @ExperimentalSerializationApi
    fun updateUser(request: UpdateUserRequest): Response = post(request, "/updateuser")

    @ExperimentalSerializationApi
    fun removeUser(request: IdRequest): Response = post(request, "/removeuser")

    @ExperimentalSerializationApi
    internal inline fun <reified T : Response, reified R> post(request: R, urlPath: String): T {
        return communicator.doPost(urlPath, request, null)
    }
}