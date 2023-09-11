FROM openjdk:17-jdk
COPY build/libs/*.jar transaction.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "transaction.jar"]
