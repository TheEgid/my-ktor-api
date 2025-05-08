# my-ktor-api/Dockerfile

FROM gradle:8.4.0-jdk17-alpine AS build

WORKDIR /app

COPY /build.gradle.kts /settings.gradle.kts /gradle.properties ./
COPY /gradle ./gradle
COPY /src ./src

RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/build/libs/app.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
