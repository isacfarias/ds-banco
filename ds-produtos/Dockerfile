FROM openjdk:11

VOLUME /tmp

ADD ./target/ds-produtos-0.0.1-SNAPSHOT.jar ds-produtos.jar

ENTRYPOINT ["java", "-jar", "ds-produtos.jar"]