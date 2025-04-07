FROM maven:3.9.8-amazoncorretto-21 AS build
WORKDIR /home/app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /home/app/target/ms-reserva-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]