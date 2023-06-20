FROM openjdk:17-alpine
MAINTAINER Artur Silva
EXPOSE 8080
RUN ./gradlew clean build
COPY build/libs/javadocker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]