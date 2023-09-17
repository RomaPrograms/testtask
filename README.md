# Documentation

### Idea of the task:

Authentication service generates information about transactions. Each transaction contains following information:
 * ID (integer)
 * Timestamp
 * Type (string)
 * Actor (string)
 * Transaction data (key-value map of strings)

The transactions must be collected by a new service. The service should receive the data at HTTP interface, store them in SQL database and make them available via the HTTP interface.
Implement the service for CRUD (Create Update Delete) and search operations. Suggest and design what the search operation should look like to be usable.
Implement it as a Spring application using MySQL database.

### Building
1. Build jars with _./gradlew bootJar_ for authentication and transaction services.
2. Build _docker-compose.yml_ file from _docker_ directory(this directory contains environment variables).
3. Run _docker-compose.yml_ file.

### Testing
**Authentication service** can be tested with swagger: http://localhost:8081/swagger-ui/index.html

**Transaction service** can be tested with swagger: http://localhost:8080/swagger-ui/index.html

#### Testing scenario:
1. Launch both services with a help of **docker/docker-compose.yml**;
2. Add transactions through **authentication** service swagger;
3. Update/Delete/Create transactions through **transaction** service swagger.

### What can be added:
 1. Transactions table columns can be updated depending on requirements.
 2. Integration and unit tests can be added.
 3. For future table structure updates can be added flyway.
