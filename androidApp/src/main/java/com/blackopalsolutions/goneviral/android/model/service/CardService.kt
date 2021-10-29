package com.blackopalsolutions.goneviral.android.model.service

import com.blackopalsolutions.goneviral.android.model.service.handler.CardMessageHandler
import com.blackopalsolutions.goneviral.android.task.GetCardTask
import com.blackopalsolutions.goneviral.model.domain.Card
import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.model.response.GetCardResponse
import kotlinx.coroutines.*
import java.lang.Exception

class CardService {
    fun getCard(request: IdRequest, observer: ServiceObserver<GetCardResponse>) {
        GlobalScope.launch {
            val task = getCardTask(request, observer)
            task.run()
        }
    }

    private fun getCardTask(request: IdRequest, observer: ServiceObserver<GetCardResponse>): GetCardTask {
        return GetCardTask(request.id, GetCardMessageHandler(observer))
    }

    class GetCardMessageHandler(private val observer: ServiceObserver<GetCardResponse>): CardMessageHandler() {
        override fun handleSuccessMessage(card: Card) {
            observer.onSuccess(GetCardResponse(card))
        }

        override fun handleFailedMessage(message: String) {
            observer.onFailure(GetCardResponse(message))
        }

        override fun handleExceptionMessage(exception: Exception) {
            observer.handleException(exception)
        }

    }
}