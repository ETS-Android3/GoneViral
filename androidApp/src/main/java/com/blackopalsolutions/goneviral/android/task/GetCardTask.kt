package com.blackopalsolutions.goneviral.android.task

import android.os.Bundle
import com.blackopalsolutions.goneviral.android.BuildConfig
import com.blackopalsolutions.goneviral.android.model.service.handler.MessageHandler
import com.blackopalsolutions.goneviral.android.util.JsonUtility
import com.blackopalsolutions.goneviral.model.domain.Card
import com.blackopalsolutions.goneviral.model.domain.User
import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.net.RequestException
import java.io.IOException
import java.lang.RuntimeException

class GetCardTask(val id: Int, handler: MessageHandler) : BackgroundTask(handler) {

    override fun doTask() {
        val request = IdRequest(id)
        try {
            val response = getServerFacade().getCard(request)
            val card = response.card
            if (card == null) {
                sendFailedMessage("Card couldn't be retrieved")
            } else {
                sendSuccessMessage(card)
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
        if (BuildConfig.DEBUG && data.size != 1) {
            error("Assertion failed")
        }
        val msgBundle = Bundle()
        msgBundle.putBoolean(MessageHandler.SUCCESS_KEY, true)
        msgBundle.putString(CARD_KEY, JsonUtility().encodeCard(data[0] as Card))
        return msgBundle
    }

    companion object {
        const val CARD_KEY = "card"
    }
}
