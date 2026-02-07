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
# maven-shade-plugin creates a jar with original name, not with -jar-with-dependencies suffix
COPY --from=build /app/target/contact-manager-*.jar app.jar

EXPOSE 7000

CMD ["java", "-jar", "app.jar"]