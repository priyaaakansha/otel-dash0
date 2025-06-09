FROM eclipse-temurin:24-jdk AS build

WORKDIR /app

COPY JavaClient.java .

RUN javac JavaClient.java

FROM eclipse-temurin:24-jre

WORKDIR /app

COPY --from=build /app/JavaClient.class .

CMD ["java", "JavaClient"]
