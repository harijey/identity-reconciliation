## Use a base image with Java and Maven installed
#FROM maven:3.8.2-openjdk-11-slim AS build
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the Maven project files to the container
#COPY pom.xml .
#
## Download the project dependencies
#RUN mvn dependency:go-offline -B
#
## Copy the application source code to the container
#COPY src ./src
#
## Build the application
#RUN mvn package -DskipTests
#
## Use a lightweight base image with JRE only
#FROM adoptopenjdk:11-jre-hotspot AS runtime
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the application JAR file from the build stage
#COPY --from=build /app/target/identity-reconciliation.jar .
#
## Set the command to run the application
#CMD ["java", "-jar", "identity-reconciliation.jar"]