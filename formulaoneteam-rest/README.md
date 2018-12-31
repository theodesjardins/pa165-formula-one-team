# Formula One Team REST module

This is the REST API module on Formula One Team management application. This module is documented using Swagger2 library.

For information about the API usage run the project using following commands run in the project root folder:
```
mvn clean install
cd formulaoneteam-rest
mvn cargo:run
```
Or using only one command run in the project root:
`mvn clean install && mvn -f formulaoneteam-web/pom.xml cargo:run`

And then open your browser and access this url http://localhost:8080/pa165/swagger-ui.html for web documentation of the API. For accessing the json configuration of Swagger visit this url: http://localhost:8080/pa165/rest/v2/api-docs.