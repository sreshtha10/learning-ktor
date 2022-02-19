package com.sreshtha

import com.sreshtha.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*

fun main(){
    embeddedServer(Netty, port = 8080, host = "0.0.0.0"){

        install(Routing){
            configureRouting()
        }


    }.start()
}

