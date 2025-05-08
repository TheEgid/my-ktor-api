package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.jsonRoutes() {
    get("/json-serialization") {
        call.respond(mapOf("hello" to "world"))
    }
}
