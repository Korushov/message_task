FROM openjdk:19
ADD /target/message_task-0.0.1-SNAPSHOT.jar message.jar
ENTRYPOINT ["java", "-jar", "message.jar"]

EXPOSE 8080/tcp