FROM openjdk:20-ea-17-slim
COPY *.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]