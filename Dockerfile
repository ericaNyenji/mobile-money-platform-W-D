# Use an official Java 17 runtime as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom files first (for caching dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the project
RUN ./mvnw package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the JAR (replace with the actual name of your jar if different)
CMD ["java", "-jar", "target/MobileMoneyPlatform-0.0.1-SNAPSHOT.jar"]
