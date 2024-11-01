package com.example.plugins

import client.HttpPut
import client.httpGet
import client.isValidEmail
import io.ktor.http.HttpStatusCode
//import java.sql.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//private const val DB_URL: String = "jdbc:sqlite:identifier.sqlite"

fun Application.configureRouting() {
    //val connection = DriverManager.getConnection(DB_URL)

    routing {
        get("/") {
            call.respondText("Hello!")
        }
        get("/login/{email}/{password}") {
            val email = call.request.queryParameters["email"]
            val password = call.request.queryParameters["password"]

            if (email == null || password == null || !isValidEmail(email)) {
                call.response.status(HttpStatusCode.BadRequest)
            }
            else {
                val request = httpGet("/backend/login", "email=$email\npassword=$password")
                if (request.status == HttpStatusCode.OK) {
                    call.response.status(HttpStatusCode.OK)
                }
            }
        }
        get("/register/{email}/{password}") {
            val email = call.request.queryParameters["email"]
            val password = call.request.queryParameters["password"]

            if (email == null || password == null || !isValidEmail(email)) {
                call.response.status(HttpStatusCode.BadRequest)
            }
            else {
                val request = httpGet("/backend/register", "email=$email\npassword=$password")
                if (request.status == HttpStatusCode.OK) {
                    call.response.status(HttpStatusCode.OK)
                }
            }
        }
    }
}
