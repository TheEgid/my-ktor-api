package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.greetRoutes() {
    get("/greet/{name}") {
        val name = call.parameters["name"] ?: "Guest"
        val lang = call.request.queryParameters["lang"] ?: "en"

        val greeting = when (lang.lowercase()) {
            "ru" -> "Привет, $name!"
            "es" -> "Hola, $name!"
            "fr" -> "Bonjour, $name!"
            else -> "Hello, $name!"
        }

        call.respond(mapOf("greeting" to greeting))
    }
}
