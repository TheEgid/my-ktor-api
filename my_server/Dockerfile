# ---------- Этап сборки ----------
FROM gradle:8.4.0-jdk17-alpine AS build

WORKDIR /app

# Копируем конфигурационные файлы отдельно для кэширования
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle gradle

# Устанавливаем права
RUN mkdir -p /home/gradle/.gradle && \
chown -R gradle:gradle /home/gradle && \
chown -R gradle:gradle /app

USER gradle

# Предзагрузка зависимостей
RUN gradle dependencies --no-daemon --configuration-cache

# Копируем исходники
COPY --chown=gradle:gradle src src

# Сборка fat jar через shadowJar
RUN gradle shadowJar --no-daemon --stacktrace --configuration-cache \
-Dorg.gradle.jvmargs="-Xmx2048m -XX:MaxMetaspaceSize=512m"

# ---------- Этап исполнения ----------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Копируем jar из сборочного этапа
COPY --from=build /app/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+HeapDumpOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]

# HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1
