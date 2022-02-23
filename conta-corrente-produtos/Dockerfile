FROM openjdk:11

VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} conta-corrente-produtos.jar

ENTRYPOINT ["java", "-jar", "conta-corrente-produtos.jar"]