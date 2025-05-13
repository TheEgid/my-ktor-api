group = "org.example"
version = "0.0.1"

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.10")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.10")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.10")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.10")
    implementation("io.ktor:ktor-server-status-pages:2.3.10")
    implementation("io.ktor:ktor-server-cors:2.3.10")
    implementation("io.ktor:ktor-server-default-headers:2.3.10")
    implementation("io.ktor:ktor-server-forwarded-header:2.3.10")
    implementation("io.ktor:ktor-server-call-logging:2.3.10")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("io.insert-koin:koin-ktor:3.5.3")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.3")

    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")

    testImplementation("io.ktor:ktor-server-tests:2.3.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:2.0.0")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveFileName.set("app.jar")
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to "io.ktor.server.netty.EngineMain"))
    }
}
