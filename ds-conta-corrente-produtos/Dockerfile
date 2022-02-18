FROM openjdk:11

VOLUME /tmp

ADD ./target/ds-conta-corrente-produtos-0.0.1-SNAPSHOT.jar ds-conta-corrente-produtos.jar

ENTRYPOINT ["java", "-jar", "ds-conta-corrente-produtos.jar"]