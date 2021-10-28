package com.blackopalsolutions.goneviral.android.model.service

import com.blackopalsolutions.goneviral.model.response.Response

interface ServiceObserver<R: Response> {
    fun onSuccess(response: R)
    fun onFailure(response: R)
    fun handleException(exception: Exception)
}