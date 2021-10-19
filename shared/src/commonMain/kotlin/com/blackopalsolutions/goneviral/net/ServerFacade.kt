package com.blackopalsolutions.goneviral.net

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

class ServerFacade {
    private val serverUrl = "https://cnatx8j1tg.execute-api.us-east-2.amazonaws.com/dev"

    @PublishedApi
    internal val communicator = ClientCommunicator(serverUrl)
/*
    fun getCard(request: IdRequest): GetCardResponse = return post(request, "/getcard")

    fun getGoal(request: IdRequest): GetGoalResponse = return post(request, "/getgoal")

    fun getGoalFromRole(request: IdRequest): GetGoalResponse
        = return post(request, "/getgoalfromrole")

    fun getRole(request: IdRequest): GetRoleResponse = return post(request, "/getrole")

    fun getRoleFromGoal(request: IdRequest): GetRoleResponse
        = return post(request, "/getrolefromgoal")

    fun getUser(request: IdRequest): GetUserResponse = post(request, "/getuser")

    fun insertUsers(request: InsertUsersRequest): Response = post(request, "/insertusers")

    fun updateUser(request: UpdateUserRequest): Response = post(request, "/updateuser")

    fun removeUser(request: IdRequest): Response = post(request, "/removeuser")

    @ExperimentalSerializationApi
    internal inline fun <reified T: Response> post(request: Serializable, urlPath: String) {
        val response = communicator.doPost<T>(urlPath, request, null)
    }
 */
}