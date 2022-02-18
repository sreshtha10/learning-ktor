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

        get("/response"){
            //call.respondText("Something went wrong!", status = HttpStatusCode.NotFound)
            //Sending json as a response
            val user = User(email = "sreshtha.mehrotra@gmail.com", password = "123")
            call.respond(user)
        }


        get("/headers"){
            //adding headers to our response.
            call.response.headers.append(name = "server-name", value = "ktor-server")
            call.respondText { "Header attached" }
        }

        get("/fileDownload"){
            val file  = File("files/img1.jpg")
            // downloading files
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName,
                    "image.jpg"
                ).toString()
            )

            call.respondFile(file)
        }


        get("/fileOpen"){
            val file= File("files/img1.jpg")
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Inline.withParameter(
                    ContentDisposition.Parameters.FileName, "open.jpg"
                ).toString()
            )
          call.respondFile(file)
        }



    }
}

@kotlinx.serialization.Serializable
data class User(
    val email:String,
    val password:String
)