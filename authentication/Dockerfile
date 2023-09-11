FROM openjdk:17-jdk
COPY build/libs/*.jar authentication.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "authentication.jar"]
