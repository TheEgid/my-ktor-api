package org.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.healthRoutes() {
    get("healthcheck") {
        call.respondText("ok", ContentType.Text.Plain)
    }
}
