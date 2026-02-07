FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build JAR with dependencies
RUN mvn clean package && \
    ls -la target/

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the shaded JAR (with dependencies)
# maven-shade-plugin creates a jar with original name
# The clean package ensures only one version exists
COPY --from=build /app/target/contact-manager-1.0.0.jar app.jar

EXPOSE 10000

CMD ["java", "-jar", "app.jar"]