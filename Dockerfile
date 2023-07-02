FROM maven:3.8.6 as build
WORKDIR /app
COPY pom.xml .
COPY src /src
RUN mvn clean install

FROM maven:3.8.6
COPY --from=build /app/target/identity-reconciliation-0.0.1-SNAPSHOT.jar /usr/app/identity-reconciliation-0.0.1-SNAPSHOT.jar
EXPOSE 9876
ENTRYPOINT ["java","-jar","/usr/app/identity-reconciliation-0.0.1-SNAPSHOT.jar"]
