package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.rootRoutes() {
    get("/") { // Явная обработка /api/
        call.respondText("API Root with slash", ContentType.Text.Plain)
    }
}
