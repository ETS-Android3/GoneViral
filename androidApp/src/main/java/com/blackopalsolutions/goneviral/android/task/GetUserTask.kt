package com.blackopalsolutions.goneviral.android.task

import android.os.Bundle
import com.blackopalsolutions.goneviral.android.model.service.handler.MessageHandler
import com.blackopalsolutions.goneviral.android.util.JsonUtility
import com.blackopalsolutions.goneviral.model.domain.User
import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.net.RequestException
import java.io.IOException
import java.lang.RuntimeException

class GetUserTask(val id: Int, handler: MessageHandler) : BackgroundTask(handler) {

    override fun doTask() {
        val request = IdRequest(id)
        try {
            val response = getServerFacade().getUser(request)
            val user = response.user
            if (user == null) {
                sendFailedMessage("User couldn't be retrieved")
            } else {
                sendSuccessMessage(user)
            }
        } catch (e: IOException) {
            sendExceptionMessage(e)
        } catch (e: RequestException) {
            sendExceptionMessage(e)
        } catch (e: RuntimeException) {
            e.message?.let { sendFailedMessage(it) }
        }
    }

    override fun createSuccessMessage(vararg data: Any): Bundle {
        assert(data.size == 1)
        val msgBundle = Bundle()
        msgBundle.putBoolean(MessageHandler.SUCCESS_KEY, true)
        msgBundle.putString(USER_KEY, JsonUtility().encodeUser(data[0] as User))
        return msgBundle
    }

    companion object {
        const val USER_KEY = "user"
    }
}
