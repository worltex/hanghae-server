FROM openjdk:17-jdk-slim

ENV TZ=Asia/Seoul

WORKDIR /app

COPY build/libs/ticketing.jar /app/ticketing.jar

EXPOSE 8080

ENTRYPOINT java \
  -jar /app/ticketing.jar \
  --spring.profiles.active=${PROFILE} \