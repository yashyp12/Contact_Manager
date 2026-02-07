# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Build application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the shaded JAR (note: shade plugin adds -shaded suffix)
COPY --from=build /app/target/*-shaded.jar app.jar

# Expose port
EXPOSE 7000

# Run application
CMD ["java", "-jar", "app.jar"]