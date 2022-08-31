# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /serververse

COPY . .

RUN ./gradlew jar

CMD ["java", "-jar", "./build/libs/Serververse-1.0-SNAPSHOT.jar"]