package com.blackopalsolutions.goneviral.net

class RequestException(msg: String, val type: String, val stackTrace: List<String>): Exception(msg) {
}