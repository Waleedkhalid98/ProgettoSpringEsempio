FROM openjdk:17-oracle
COPY target/springSECURITYExampleV2-0.0.1-SNAPSHOT.jar /app
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app"]