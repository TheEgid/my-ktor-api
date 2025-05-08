# my-ktor-api/Dockerfile

FROM gradle:8.4.0-jdk17-alpine AS build

WORKDIR /app

COPY my_server/build.gradle.kts my_server/settings.gradle.kts my_server/gradle.properties ./
COPY my_server/gradle ./gradle
COPY my_server/src ./src

RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/build/libs/app.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
