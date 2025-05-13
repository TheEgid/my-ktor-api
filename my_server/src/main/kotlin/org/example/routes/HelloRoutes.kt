package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class HelloResponse(
    val hello: String,
    val timestamp: String,
)

fun getHello(): HelloResponse {
    return HelloResponse(
        hello = "Hello Kotlin",
        timestamp = LocalDateTime.now().toString(),
    )
}

fun Route.helloRoutes() {
    get("hello") {
        call.respond(getHello())
    }
}
