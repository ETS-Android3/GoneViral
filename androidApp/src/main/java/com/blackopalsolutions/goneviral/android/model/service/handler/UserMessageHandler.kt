package com.blackopalsolutions.goneviral.android.model.service.handler

import android.os.Looper
import android.os.Message
import com.blackopalsolutions.goneviral.android.task.GetUserTask
import com.blackopalsolutions.goneviral.android.util.JsonUtility
import com.blackopalsolutions.goneviral.model.domain.User

abstract class UserMessageHandler(looper: Looper): MessageHandler(looper) {

    override fun handleSuccessMessage(message: Message) {
        val json = message.data.getString(GetUserTask.USER_KEY)
        if (json == null) {
            handleFailedMessage("Couldn't decode user object")
        } else {
            val user = JsonUtility().decodeUser(json)
            handleSuccessMessage(user)
        }
    }

    protected abstract fun handleSuccessMessage(user: User)
}