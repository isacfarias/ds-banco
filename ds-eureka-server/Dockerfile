FROM openjdk:11

VOLUME /tmp

ADD ./target/ds-eureka-server-0.0.1-SNAPSHOT.jar ds-eureka-server.jar

ENTRYPOINT ["java", "-jar", "ds-eureka-server.jar"]