package org.example

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.example.routes.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        route("/api") {
            rootRoutes()
            healthRoutes()
            helloRoutes()
            greetRoutes()
        }
    }
}
