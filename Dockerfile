FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

# Copy only Gradle files first (better caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew --no-daemon dependencies

# Copy source and build
COPY src src
RUN ./gradlew --no-daemon bootJar -x test


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
