FROM openjdk:17-alpine
EXPOSE 8083
COPY . .
RUN ./gradlew clean build -x