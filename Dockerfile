FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and make it executable
COPY mvnw .
RUN chmod +x mvnw

COPY .mvn .mvn
COPY pom.xml .

# Pre-download dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Package the app
RUN ./mvnw package -DskipTests

EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/*.jar"]
