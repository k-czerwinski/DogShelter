# DogShelter
App created with Spring Boot and PostgresSQL.
The control panel allows to add, adopt or view all dogs that are stored in the database.
## Getting started
Before starting initialize manually PostgreSQL database with the name "dogshelter". Adjust the following lines(especially username and password) in ```src/main/resources/application.properties``` file to your local credentials.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/dogshelter
spring.datasource.username=postgres
spring.datasource.password=postgres
```
That's all!
## Tests
The app includes unit tests created with JUnit.\
Queries in ```DogRepository``` class are tested using annotation @DataJpaTest on H2 embedded database.\
```DogService``` class is tested using Mockito.