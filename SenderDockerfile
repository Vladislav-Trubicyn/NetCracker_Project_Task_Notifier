FROM openjdk:11
COPY ./target/sender-0.0.1-SNAPSHOT.jar /usr/src/sender/
WORKDIR /usr/src/sender/
EXPOSE 8081
CMD ["java","-jar","sender-0.0.1-SNAPSHOT.jar"]