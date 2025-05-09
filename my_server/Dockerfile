# Этап сборки
FROM gradle:8.4.0-jdk17-alpine AS build

WORKDIR /app

# Копируем конфигурационные файлы отдельно — для лучшего кэширования
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle gradle

# Устанавливаем правильные права
RUN mkdir -p /home/gradle/.gradle && \
    chown -R gradle:gradle /home/gradle && \
    chown -R gradle:gradle /app

USER gradle

# Скачиваем зависимости
RUN gradle dependencies --no-daemon --configuration-cache

# Копируем исходный код
COPY --chown=gradle:gradle src src
# COPY --chown=gradle:gradle resources resources

# Сборка fat JAR
RUN gradle buildFatJar --no-daemon --stacktrace \
    --configuration-cache \
    -Dorg.gradle.jvmargs="-Xmx2048m -XX:MaxMetaspaceSize=512m"

# Этап выполнения
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+HeapDumpOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
