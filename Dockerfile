FROM amazoncorretto:11-alpine-jdk
ADD target/identity-reconciliation.jar identity-reconciliation.jar
ENTRYPOINT ["java", "-jar", "/identity-reconciliation.jar"]