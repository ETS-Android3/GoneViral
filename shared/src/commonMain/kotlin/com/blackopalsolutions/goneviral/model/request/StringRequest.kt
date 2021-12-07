package com.blackopalsolutions.goneviral.model.request

class StringRequest(val value: String): Request() {

    override fun encodeToJsonString(): String =
        "{value:${value}"
}