package com.blackopalsolutions.goneviral.net

import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.model.response.GetUserResponse
import com.blackopalsolutions.goneviral.model.response.Response
import kotlinx.serialization.ExperimentalSerializationApi

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
*/
    @ExperimentalSerializationApi
    fun getUser(request: IdRequest): GetUserResponse = post(request, "/getuser")

    /*

    fun insertUsers(request: InsertUsersRequest): Response = post(request, "/insertusers")

    fun updateUser(request: UpdateUserRequest): Response = post(request, "/updateuser")

    fun removeUser(request: IdRequest): Response = post(request, "/removeuser")
     */

    @ExperimentalSerializationApi
    internal inline fun <reified T: Response, reified R> post(request: R, urlPath: String) : T {
        val response = communicator.doPost<T>(urlPath, request, null)
        return response
    }
}