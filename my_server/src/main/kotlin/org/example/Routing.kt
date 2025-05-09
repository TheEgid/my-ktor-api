package org.example

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.example.routes.*

fun Application.configureRouting() {
    routing {
        route("/api") {
            rootRoutes()
            healthRoutes()
            helloRoutes()
            greetRoutes()
        }
    }
}
