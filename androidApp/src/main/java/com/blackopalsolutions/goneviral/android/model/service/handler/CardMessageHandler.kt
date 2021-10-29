package com.blackopalsolutions.goneviral.android.model.service.handler

import android.os.Message
import com.blackopalsolutions.goneviral.android.task.GetCardTask
import com.blackopalsolutions.goneviral.android.util.JsonUtility
import com.blackopalsolutions.goneviral.model.domain.Card

abstract class CardMessageHandler: MessageHandler() {

    override fun handleSuccessMessage(message: Message) {
        val json = message.data.getString(GetCardTask.CARD_KEY)
        if (json == null) {
            handleFailedMessage("Couldn't decode card object")
        } else {
            val card = JsonUtility().decodeCard(json)
            handleSuccessMessage(card)
        }
    }

    protected abstract fun handleSuccessMessage(card: Card)
}