FROM bellsoft/liberica-openjdk-alpine

RUN mkdir -p /app/app
WORKDIR /app/

COPY target/shortener-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "shortener-0.0.1-SNAPSHOT.jar"]