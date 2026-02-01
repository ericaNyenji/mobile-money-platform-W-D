# ===============================
# 1️⃣ Build stage
# ===============================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first (for dependency caching)
COPY pom.xml .
#RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn -B clean package -DskipTests


# ===============================
# 2️⃣ Runtime stage
# ===============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# JVM container optimizations
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
