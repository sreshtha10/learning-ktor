package com.sreshtha

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.sreshtha.plugins.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(){
    embeddedServer(Netty, port = 8080, host = "0.0.0.0"){
        install(Routing){
            homeRoute()
        }
    }.start()
}

fun Routing.homeRoute(){
    get() {
        call.respond("Hello KTOR")
    }

    post() {

    }
}
