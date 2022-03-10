FROM openjdk:11
COPY ./target/notification-0.0.1-SNAPSHOT.jar /usr/src/notification/
WORKDIR /usr/src/notification/
EXPOSE 8080
CMD ["java","-jar","notification-0.0.1-SNAPSHOT.jar"]