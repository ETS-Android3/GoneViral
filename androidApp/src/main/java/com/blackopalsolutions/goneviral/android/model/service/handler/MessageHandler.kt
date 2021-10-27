package com.blackopalsolutions.goneviral.android.model.service.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.lang.Exception

abstract class MessageHandler : Handler(Looper.getMainLooper()) {

    override fun handleMessage(msg: Message) {
        handleBeforeMessage()
        val success = msg.data.getBoolean(SUCCESS_KEY)
        if (success) {
            handleSuccessMessage(msg)
        } else if (msg.data.containsKey(MESSAGE_KEY)) {
            msg.data.getString(MESSAGE_KEY)?.let { handleFailedMessage(it) }
        } else if (msg.data.containsKey(EXCEPTION_KEY)) {
            msg.data.getSerializable(EXCEPTION_KEY)?.let { handleExceptionMessage(it as Exception) }
        }
        handleAfterMessage()
    }

    protected open fun handleBeforeMessage() {}

    protected open fun handleAfterMessage() {}

    protected abstract fun handleSuccessMessage(message: Message)

    protected abstract fun handleFailedMessage(message: String)

    protected abstract fun handleExceptionMessage(exception: Exception)

    companion object {
        const val SUCCESS_KEY = "success"
        const val MESSAGE_KEY = "message"
        const val EXCEPTION_KEY = "exception"
    }
}