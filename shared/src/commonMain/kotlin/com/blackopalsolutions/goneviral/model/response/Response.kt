package com.blackopalsolutions.goneviral.model.response

open class Response(val success: Boolean, val message: String) {
    constructor(success: Boolean) : this(success, "")
    constructor(message: String) : this(false, message)
    constructor() : this(false, "")
}