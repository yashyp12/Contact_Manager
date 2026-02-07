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

# Copy the assembled JAR (with dependencies)
COPY --from=build /app/target/*-jar-with-dependencies.jar app.jar

EXPOSE 7000

CMD ["java", "-jar", "app.jar"]