FROM openjdk:19-jdk-slim

WORKDIR /app

COPY target/moysklad-1.0-SNAPSHOT.jar /app/moysklad.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/moysklad
ENV SPRING_DATASOURCE_USERNAME=
ENV SPRING_DATASOURCE_PASSWORD=

EXPOSE 8080

CMD  ["java", "-jar", "moysklad.jar"]
