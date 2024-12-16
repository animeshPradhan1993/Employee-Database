# locally compiling the application

navigate to the project root directory and run ./mvnw clean install
# running the app locally

navigate to the project root directory and run ./mvnw spring-boot:run

The endpoints can be accessed at localhost:8080 this setting can be  modified by changing the application port
in application.yaml

none of the endpoints in this application are secured

Employee with admin role is already created with Id 1
Application uses a H2 database, console can be accessed at host:port/h2-console/  no authentication required

To start following roles are created
Role ID, Role name
1,        ADMIN
2,        USER
3,        MANAGER

The swagger can be found at src/main/resources/swagger/Employee-Database.yaml

Jacoco is used to generate the coverage reports.
Coverage reports(index.html) can be accessed in the site folder inside the generated folder
on deletion of a role all the projects will be assigned to Employee 1

Please donot delete the employee 1 and role 1 as employee 1 is the the default employee to whom the projects are assigned.
