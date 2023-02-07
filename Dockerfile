FROM openjdk:20-ea-19-slim
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]