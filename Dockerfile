FROM openjdk:17-alpine
EXPOSE 8080
COPY . .
RUN ./gradlew clean build