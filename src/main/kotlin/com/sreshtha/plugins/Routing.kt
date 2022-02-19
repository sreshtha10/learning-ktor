package com.sreshtha.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respond("Hello World!")
        }


    }
}

@kotlinx.serialization.Serializable
data class User(
    val email:String,
    val password:String
)