package com.blackopalsolutions.goneviral.android.model.service

import android.os.Looper
import com.blackopalsolutions.goneviral.android.model.service.handler.UserMessageHandler
import com.blackopalsolutions.goneviral.android.task.GetUserTask
import com.blackopalsolutions.goneviral.model.domain.User
import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.model.response.GetUserResponse
import kotlinx.coroutines.*
import java.lang.Exception

class UserService {
    fun getUser(request: IdRequest, observer: ServiceObserver<GetUserResponse>) {
        GlobalScope.launch {
            Looper.prepare()
            val task = getUserTask(request, observer)
            task.run()
        }
    }

    private fun getUserTask(request: IdRequest, observer: ServiceObserver<GetUserResponse>): GetUserTask {
        return GetUserTask(request.id, GetUserMessageHandler(observer, Looper.getMainLooper()))
    }

    class GetUserMessageHandler(private val observer: ServiceObserver<GetUserResponse>, looper: Looper): UserMessageHandler(looper) {
        override fun handleSuccessMessage(user: User) {
            observer.onSuccess(GetUserResponse(user))
        }

        override fun handleFailedMessage(message: String) {
            observer.onFailure(GetUserResponse(message))
        }

        override fun handleExceptionMessage(exception: Exception) {
            observer.handleException(exception)
        }

    }
}