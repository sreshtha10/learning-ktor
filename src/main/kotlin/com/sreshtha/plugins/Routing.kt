package com.sreshtha.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respond("Hello World!")
        }
        get("/contactUs"){
            call.respond("Contact Us!")
        }

        // call obj is used to make request, respond etc.
        get("/request"){
            println("URI :${call.request.uri}")
            println("Header :${call.request.headers.names()}")
            println("User agent: ${call.request.headers["Accept"]}")
            // URL Query Parameters.
            println("Query Params: ${call.request.queryParameters.names()}")
            println("Name: ${call.request.queryParameters["name"]}") // eg. ?name=x&email=y
            call.respondText("REQUEST")
        }

        get("/iphones/{page}"){
            // Accessing URL parameters eg.  www.abc.com/1/2
            println(call.parameters.names())
            call.respond("You are on page number:  ${call.parameters["page"]}")
        }

        post("/login") {
            // getting Body of post request via Serialization and Content Negotiation (which is of type json)
            val user = call.receive<User>()
            println(user)
            call.respondText("Login")
        }


    }
}

@kotlinx.serialization.Serializable
data class User(
    val email:String,
    val password:String
)