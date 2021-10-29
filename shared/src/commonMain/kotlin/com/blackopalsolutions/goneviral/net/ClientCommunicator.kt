package com.blackopalsolutions.goneviral.net

import com.blackopalsolutions.goneviral.model.request.Request
import kotlinx.serialization.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.serialization.json.Json

class ClientCommunicator(private val baseUrl: String) {
    @PublishedApi
    internal val timeOutMillis = 10000

    @PublishedApi
    internal interface RequestStrategy {
        fun setRequestMethod(conn: HttpURLConnection)
        fun sendRequest(conn: HttpURLConnection)
    }

    fun getUrl(url: String): URL {
        val str = baseUrl + (if (url.startsWith("/")) "" else "/") + url
        return URL(str)
    }

    @ExperimentalSerializationApi
    inline fun <reified T> doPost(urlPath: String, requestInfo: Request,
                                  headers: Map<String, String>?): T {
        val strategy = object: RequestStrategy {
            override fun setRequestMethod(conn: HttpURLConnection) {
                conn.setRequestMethod("POST")
            }

            override fun sendRequest(conn: HttpURLConnection) {
                conn.setDoOutput(true)

                val entityBody = requestInfo.encodeToJsonString()
                var os : OutputStream? = null

                try {
                    os = conn.getOutputStream()
                    os.write(entityBody.encodeToByteArray())
                    os.flush()
                } catch (ignored: IOException) {}
                finally {
                    if (os != null) {
                        os.close()
                    }
                }
            }
        }

        return doRequest(urlPath, headers, strategy)
    }

    @ExperimentalSerializationApi
    @PublishedApi
    internal inline fun <reified T> doRequest(urlPath: String, headers: Map<String, String>?,
                                              strategy: RequestStrategy): T {
        var conn: HttpURLConnection? = null

        try {
            val url = getUrl(urlPath)
            conn = url.openConnection() as HttpURLConnection
            conn.setReadTimeout(timeOutMillis)
            strategy.setRequestMethod(conn)

            headers?.keys?.forEach { key ->
                conn.setRequestProperty(key, headers[key])
            }

            strategy.sendRequest(conn)

            when (conn.getResponseCode()) {
                HttpURLConnection.HTTP_OK -> {
                    val responseString = getResponse(conn.getInputStream())
                    return Json.decodeFromString(responseString)
                }
                HttpURLConnection.HTTP_BAD_REQUEST
                        or HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                    val response = getErrorResponse(conn)
                    throw RequestException(response.errorMessage,
                        response.errorType, response.stackTrace)
                }
                else -> throw RuntimeException("An unknown error occurred." +
                        " Response code = " + conn.getResponseCode())
            }
        } finally {
            conn?.disconnect()
        }
    }

    @ExperimentalSerializationApi
    @PublishedApi
    internal fun getErrorResponse(conn: HttpURLConnection): ErrorResponse {
        val responseString = getResponse(conn.getErrorStream())
        if (responseString == "") {
            throw RuntimeException("No response for code " + conn.getResponseCode())
        }
        return Json.decodeFromString(responseString)
    }

    @PublishedApi
    internal fun getResponse(inputStream: InputStream): String {
        var response = ""
        try {
            val reader = BufferedReader(InputStreamReader(inputStream))
            val iterator = reader.lines().iterator()
            while(iterator.hasNext()) {
                response += iterator.next()
            }
        } catch(ignored: Exception) {}
        return response
    }

    @Serializable
    data class ErrorResponse(val errorMessage: String, val errorType: String,
                             val stackTrace: List<String>)
}