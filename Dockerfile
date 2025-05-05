# Сборка
FROM gradle:8.4.0-jdk17-alpine AS builder
WORKDIR /app

# Копируем файлы сборки первыми для кеширования
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties .
COPY src ./src

# Собираем JAR
RUN gradle clean buildFatJar --no-daemon

# Финальный образ
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копируем JAR и конфиги
COPY --from=builder /app/build/libs/*-all.jar app.jar
COPY --from=builder /app/resources ./resources

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]