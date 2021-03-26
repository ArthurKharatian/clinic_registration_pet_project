FROM openjdk:11-jre-slim

VOLUME /tmp

ADD /target/clinic-registration-spring-boot.jar clinic-registration-spring-boot.jar

ENTRYPOINT ["java", "-jar", "/clinic-registration-spring-boot.jar"]