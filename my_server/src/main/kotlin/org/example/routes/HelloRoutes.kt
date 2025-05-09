package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.helloRoutes() {
    get("/hello") {
        call.respond(mapOf("hello" to "world"))
    }
}


