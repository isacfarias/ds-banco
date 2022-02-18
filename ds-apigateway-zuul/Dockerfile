FROM openjdk:11

VOLUME /tmp

ADD ./target/ds-apigateway-zuul-0.0.1-SNAPSHOT.jar ds-apigateway-zuul.jar

ENTRYPOINT ["java", "-jar", "ds-apigateway-zuul.jar"]