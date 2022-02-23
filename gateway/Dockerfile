FROM openjdk:11

VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} gateway.jar

ENTRYPOINT ["java",  "-jar", "gateway.jar"]