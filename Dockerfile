FROM openjdk:20-ea-17-slim
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]