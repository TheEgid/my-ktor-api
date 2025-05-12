package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
private data class HelloResponse(
    val hello: String,
    val timestamp: String,
)

fun Route.helloRoutes() {
    get("hello") {
        call.respond(
            HelloResponse(
                hello = "world",
                timestamp = LocalDateTime.now().toString(),
            ),
        )
    }
}
