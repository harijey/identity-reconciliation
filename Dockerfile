FROM amazoncorretto:11-alpine-jdk
COPY target/identity-reconciliation.jar identity-reconciliation.jar
ENTRYPOINT ["java", "-jar", "/identity-reconciliation.jar"]