package org.example

import io.ktor.server.application.*
import io.ktor.http.HttpMethod
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.event.Level


fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
    }

    install(CallLogging) {
        level = Level.INFO

        format { call ->
            val method = call.request.httpMethod.value
            val uri = call.request.uri
            val status = call.response.status()?.value?.toString()?.padStart(3, '0') ?: "000"

            "HTTP ${method.uppercase()} ${uri} → ${status}"
        }
    }
}
