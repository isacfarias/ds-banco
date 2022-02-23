FROM openjdk:11

VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} pessoa.jar

ENTRYPOINT ["java", "-jar", "pessoa.jar"]