# Stage 1: Build
FROM eclipse-temurin:17-jdk-jammy AS builder

# Set the working directory inside the builder container
WORKDIR /app

# Copy Gradle wrapper and configuration files to leverage Docker caching
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Give executable permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Pre-download dependencies to leverage Docker caching
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the source code
COPY src src

# Build the Spring Boot JAR file
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run
FROM eclipse-temurin:17-jre-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy only the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
