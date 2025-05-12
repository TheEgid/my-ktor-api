import io.ktor.plugin.features.*


group = "org.example"
version = "0.0.1"

plugins {
    kotlin("jvm") version "2.0.21"
    id("io.ktor.plugin") version "3.0.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"

    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:3.0.1")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.0.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.0.1")
    implementation("io.ktor:ktor-server-netty-jvm:3.0.1")
    implementation("io.ktor:ktor-server-status-pages-jvm:3.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("io.insert-koin:koin-ktor:3.5.3")

    // Koin
    implementation("io.insert-koin:koin-ktor:3.5.3")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.3")

    implementation("io.ktor:ktor-server-cors-jvm:3.0.1")
    implementation("io.ktor:ktor-server-forwarded-header-jvm:3.0.1")
    implementation("io.ktor:ktor-server-default-headers-jvm:3.0.1")

    testImplementation("io.ktor:ktor-server-tests-jvm:3.0.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:2.0.21")

    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    implementation("io.ktor:ktor-server-call-logging-jvm:3.0.1")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

ktor {
    fatJar {
        archiveFileName.set("app.jar")
    }
}
