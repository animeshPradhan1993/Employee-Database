

FROM openjdk:17
ADD target/employee-database-1.0.0.jar employee-database.jar
ENTRYPOINT ["java","-jar","employee-database.jar"]
