FROM openjdk:11

VOLUME /tmp

ADD ./target/ds-pessoa-0.0.1-SNAPSHOT.jar ds-pessoa.jar

ENTRYPOINT ["java", "-jar", "ds-pessoa.jar"]