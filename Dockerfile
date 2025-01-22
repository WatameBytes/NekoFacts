# Stage 1: Build
FROM eclipse-temurin:23-jdk AS builder

# Set the working directory inside the builder container
WORKDIR /app

# Pre-install Gradle
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://services.gradle.org/distributions/gradle-8.11.1-bin.zip \
    && unzip gradle-8.11.1-bin.zip -d /opt/ \
    && rm gradle-8.11.1-bin.zip \
    && ln -s /opt/gradle-8.11.1/bin/gradle /usr/bin/gradle

# Copy Gradle wrapper and configuration files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Give executable permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Pre-download dependencies to leverage Docker caching
RUN ./gradlew dependencies --no-daemon

# Copy the source code
COPY src src

# Build the Spring Boot JAR file
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run
FROM eclipse-temurin:23-jre

# Set the working directory inside the container
WORKDIR /app

# Copy only the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
