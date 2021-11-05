package com.blackopalsolutions.goneviral.model.request

class IdRequest(val id: Int): Request() {

    override fun encodeToJsonString(): String =
        "{id:${id}}"

}