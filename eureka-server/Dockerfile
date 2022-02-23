FROM openjdk:11

VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} eureka-server.jar

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]