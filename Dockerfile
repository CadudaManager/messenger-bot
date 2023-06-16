FROM openjdk:17-alpine
EXPOSE 80
COPY . .
RUN ./gradlew clean build