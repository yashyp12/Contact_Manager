FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY contact_manager/pom.xml .
RUN mvn dependency:go-offline

COPY contact_manager/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 7000

CMD ["java","-jar","app.jar"]
