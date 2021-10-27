package com.blackopalsolutions.goneviral.android.task

import android.os.Bundle
import android.os.Message
import com.blackopalsolutions.goneviral.android.model.service.handler.MessageHandler
import com.blackopalsolutions.goneviral.net.ServerFacade
import java.lang.Exception

abstract class BackgroundTask protected constructor(private val handler: MessageHandler) : Runnable {
    private var facade: ServerFacade? = null

    fun getServerFacade(): ServerFacade {
        if (facade == null) {
            facade = ServerFacade()
        }
        return facade!!
    }

    override fun run() {
        try {
            doTask()
        } catch (exception: Exception) {
            sendExceptionMessage(exception)
        }
    }

    protected abstract fun doTask()

    protected fun sendSuccessMessage(vararg data: Any) {
        val msgBundle = createSuccessMessage(data)
        val msg = Message.obtain()
        msg.data = msgBundle
        handler.handleMessage(msg)
    }

    protected open fun createSuccessMessage(vararg data: Any): Bundle {
        assert(data.isEmpty())
        val msgBundle = Bundle()
        msgBundle.putBoolean(MessageHandler.SUCCESS_KEY, true)
        return msgBundle
    }

    protected fun sendFailedMessage(message: String) {
        val msgBundle = Bundle()
        msgBundle.putBoolean(MessageHandler.SUCCESS_KEY, false)
        msgBundle.putString(MessageHandler.MESSAGE_KEY, message)

        val msg = Message.obtain()
        msg.data = msgBundle
        handler.handleMessage(msg)
    }

    protected fun sendExceptionMessage(exception: Exception) {
        val msgBundle = Bundle()
        msgBundle.putBoolean(MessageHandler.SUCCESS_KEY, false)
        msgBundle.putSerializable(MessageHandler.EXCEPTION_KEY, exception)

        val msg = Message.obtain()
        msg.data = msgBundle
        handler.handleMessage(msg)
    }
}