#Handan Yayla

## Summary
TUI DX Backend technical Test v2.
This project is a Spring Boot project with maven package management. This REST API was developed to order using H2 local database management and Java 11.<br />
Firstly, client has to register the system. After that, client can order pilotes. After receiving the order from client, the order is notificated to rabbitmq message broker service. Also, the client has chance to update the order in 5 minutes. But If the time is over, the client could not change the order. <br />
Secondly, When the project is builded, a user that has role 'ADMIN' is created. This user has some other advantages. Admin user can search and list the order with basic authentication.Searching the order is working as partial search. Admin can search writing client's first name and last name. The admin can also search orders by city, country, post code, street,phone number and email.<br />
Finally, you must run the project using docker-compose.yml file. This file will have a configuration to create a RabbitMq message broker service in a Docker container and connect it to the container of the Spring Boot project to run as a multi-container application.<br />


### Prerequisites
```
Jdk 11
Maven
Docker
```

### Run and Build
After installing necessary library, you can run the project on Command Line. 
docker-compose.yml file is responsible for running rabbitmq and spring boot project.

###### Create .jar file under the target file, Also, you can see Test results: 
```
mvn clean package
```
###### Create images and containers and run the project as detached mode:
```
docker-compose up -d 
```
Now, REST API is running, you can make a request.
### Stop Docker Container
###### Stop the containers :
```
docker-compose stop 
```
###### Stop and delete containers :
```
docker-compose down 
```
If you want to run the project with any IDE, you must run Rabbitmq service using docker-compose.yml file.
```
docker-compose up -d <service_name> 
```
### Project Information
##### Admin username and password
```
username: admin
password: admin
```
##### Spring Boot project:
```
http://localhost:8080/api/v1
```

##### H2 console:
###### You can see data 
```
http://localhost:8080/api/v1/h2-console
```

##### RabbitMQ:
###### You can see create and update order notifications in Queues tab.
```
http://localhost:15672
username: guest
password: guest
```

### REST API ENDPOINT
```
POST /client/
POST /client/{clientId}/order
PUT  /client/{clientId}/order/{orderId}
GET  /order/{partial}
GET  /orders
```
<br />
You can import postman file named TUI.postman_collection.json that is located in the project.





