FROM openjdk:8

COPY ./target/dislock-0.0.1-SNAPSHOT.jar  /var/lib/dislock/
WORKDIR /var/lib/dislock/

EXPOSE 8080
CMD ["java", "-jar", "dislock-0.0.1-SNAPSHOT.jar"]
