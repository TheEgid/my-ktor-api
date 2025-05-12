package org.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String,
    val message: String?,
)

sealed class GreetingException(message: String) : RuntimeException(message)

class InvalidNameException : GreetingException("Invalid name format. Only letters are allowed")

class UnsupportedLanguageException(lang: String) : GreetingException("Unsupported language: $lang")

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<UnsupportedLanguageException> { call, cause ->
            call.respond(
                HttpStatusCode.NotAcceptable,
                ErrorResponse(
                    error = "Language not supported",
                    message = cause.message,
                ),
            )
        }

        exception<InvalidNameException> { call, _ ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse(
                    error = "Invalid name format",
                    message = "Name should contain only letters",
                ),
            )
        }

        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(
                    error = "Internal server error",
                    message = cause.message,
                ),
            )
        }
    }
}
