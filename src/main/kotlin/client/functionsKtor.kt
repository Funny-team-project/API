package client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun httpGet(url: String, body: String): HttpResponse {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get(url) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    return response
}

suspend fun HttpPut(url: String, body: String): HttpResponse {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.put(url) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    return response
}

fun isValidEmail(email: String): Boolean {
    val pattern = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    return pattern.containsMatchIn(email)
}