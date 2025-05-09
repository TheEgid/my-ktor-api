package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import org.example.InvalidNameException
import org.example.UnsupportedLanguageException
import java.time.LocalDateTime

@kotlinx.serialization.Serializable
data class GreetingResponse(
    val greeting: String,
    val timestamp: String,
    val details: GreetingDetails? = null
)

@kotlinx.serialization.Serializable
data class GreetingDetails(
    val name_length: Int,
    val vowel_count: Int,
    val is_palindrome: Boolean
)

fun Route.greetRoutes() {
    route("/greet") {
        get {
            call.respond(
                GreetingResponse(
                    greeting = "Hello, World!",
                    timestamp = LocalDateTime.now().toString()
                )
            )
        }

        get("/{name}") {
            val name = validateName(call.parameters["name"] ?: "Guest")
            val lang = call.request.queryParameters["lang"] ?: "en"
            val details = call.request.queryParameters["details"]?.toBoolean() ?: false

            val response = GreetingResponse(
                greeting = generateGreeting(name, lang),
                timestamp = LocalDateTime.now().toString(),
                details = if (details) {
                    GreetingDetails(
                        name_length = name.length,
                        vowel_count = countVowels(name),
                        is_palindrome = isPalindrome(name)
                    )
                } else null
            )

            call.respond(response)
        }
    }
}

private fun validateName(name: String): String {
    if (name != "Guest" && !name.matches(Regex("^[a-zA-Zа-яА-Я]+$"))) {
        throw InvalidNameException()
    }
    return name
}

private fun generateGreeting(name: String, lang: String): String {
    val greetings = when (lang.lowercase()) {
        "ru" -> listOf("Привет", "Здравствуй", "Доброго времени суток")
        "es" -> listOf("Hola", "Buenos días", "¿Qué tal?")
        "fr" -> listOf("Bonjour", "Salut", "Coucou")
        "en" -> listOf("Hello", "Hi", "Welcome")
        else -> throw UnsupportedLanguageException(lang)
    }
    return "${greetings.random()}, $name!"
}

private fun countVowels(name: String): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'y', 'а', 'у', 'о', 'ы', 'и', 'э', 'я', 'ю', 'ё', 'е')
    return name.lowercase().count { it in vowels }
}

private fun isPalindrome(name: String): Boolean {
    val cleanName = name.lowercase().replace("\\s+".toRegex(), "")
    return cleanName == cleanName.reversed()
}
