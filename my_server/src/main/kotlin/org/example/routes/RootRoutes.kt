package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.rootRoutes() {
    get("/") {
        call.respondText("777 Привет World!", ContentType.Text.Plain)
    }
}
