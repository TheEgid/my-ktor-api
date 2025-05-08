# Этап сборки
FROM gradle:8.4.0-jdk17-alpine AS build

WORKDIR /app

# Копируем сначала файлы конфигурации для лучшего использования кэша Docker
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle gradle

# Устанавливаем правильные права для пользователя gradle
RUN mkdir -p /home/gradle/.gradle && \
    chown -R gradle:gradle /home/gradle && \
    chown -R gradle:gradle /app

# Переключаемся на пользователя gradle (не root)
USER gradle

# Скачиваем зависимости (кешируемый слой)
RUN gradle dependencies --no-daemon

# Копируем исходный код
COPY src src

# Собираем fat JAR с дополнительными параметрами памяти
RUN gradle buildFatJar --no-daemon --stacktrace \
    -Dorg.gradle.jvmargs="-Xmx2048m -XX:MaxMetaspaceSize=512m" \
    -Dorg.gradle.daemon=false

# Этап выполнения
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Копируем собранный JAR (используем wildcard на случай, если имя файла отличается)
COPY --from=build /app/build/libs/*.jar app.jar

# Настройки безопасности и производительности JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+HeapDumpOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
