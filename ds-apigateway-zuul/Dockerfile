FROM openjdk:11

VOLUME /tmp

ADD ./target/gateway-0.0.1-SNAPSHOT.jar gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]