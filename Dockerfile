# Используем образ с JDK для сборки
FROM gradle:8.4.0-jdk17-alpine AS build

WORKDIR /app

# Копируем только файлы, необходимые для сборки (для лучшего кэширования)
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle
COPY src ./src

# Собираем приложение
RUN gradle buildFatJar --no-daemon

# Финальный образ с JRE
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Копируем собранный JAR
COPY --from=build /app/build/libs/app.jar ./

# Копируем ресурсы если нужно
COPY src/main/resources ./resources

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
