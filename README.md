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


This service can be tested by swagger: http://localhost:8080/swagger-ui/index.html

### What can be added:
 1. Transactions table columns can be updated depending on requirements.
 2. More tests can be added.
 3. For future table structure updates can be added flyway.
