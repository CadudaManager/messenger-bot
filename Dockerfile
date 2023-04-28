FROM openjdk:17
MAINTAINER Artur Silva
COPY build/libs/bot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]