FROM maven:3.8.2-jdk-11 as build
WORKDIR /
COPY pom.xml .
COPY src /src
RUN mvn clean package -DskipTests

#FROM amazoncorretto:11-alpine-jdk
#COPY --from=build target/identity-reconciliation.jar identity-reconciliation.jar
#ENTRYPOINT ["java", "-jar", "/identity-reconciliation.jar"]

FROM amazoncorretto:11-alpine-jdk
ADD target/identity-reconciliation.jar identity-reconciliation.jar
ENTRYPOINT ["java", "-jar", "/identity-reconciliation.jar"]