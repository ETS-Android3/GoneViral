package com.blackopalsolutions.goneviral.model.request

abstract class Request {
    abstract fun encodeToJsonString(): String
}