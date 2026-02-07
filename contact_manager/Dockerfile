# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy everything
COPY pom.xml .
COPY src ./src

# Build and list what was created (for debugging)
RUN mvn clean package -DskipTests && \
    echo "=== Files in target directory ===" && \
    ls -la target/

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy ALL jar files and rename to app.jar
COPY --from=build /app/target/*.jar ./

# Rename the jar to app.jar
RUN mv *.jar app.jar && \
    echo "=== Final JAR file ===" && \
    ls -la

# Expose port
EXPOSE 7000

# Run application
CMD ["java", "-jar", "app.jar"]