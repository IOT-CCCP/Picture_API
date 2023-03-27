FROM openjdk:17-alpine

MAINTAINER tonal-all_api

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

ADD /build/libs/spring_boot_3-0.0.1-SNAPSHOT.jar /tonal-all_api.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /bookstore.jar"]

EXPOSE 8806
