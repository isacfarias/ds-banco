FROM openjdk:11

VOLUME /tmp

ADD ./target/conta-corrente-produtos-0.0.1-SNAPSHOT.jar conta-corrente-produtos.jar

ENTRYPOINT ["java", "-jar", "conta-corrente-produtos.jar"]