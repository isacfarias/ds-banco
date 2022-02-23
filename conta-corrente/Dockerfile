FROM openjdk:11

VOLUME /tmp

ADD ./target/conta-corrente-0.0.1-SNAPSHOT.jar conta-corrente.jar

ENTRYPOINT ["java", "-jar", "conta-corrente.jar"]