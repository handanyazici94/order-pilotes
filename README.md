#Handan Yayla

## Summary
TUI DX Backend technical Test v2.
This project is a Spring Boot project with maven package management. This REST API was developed
to order using H2 local database management and Java 11. Firstly, client has to register the system. 
After that, client can order pilotes. After receiving the order from client, the order is notificated to rabbitmq message broker service.
Also, the client has chance to update the order in 5 minutes. But If the time is over, the client could not change the order.
Secondly, When the project build, an user that has role 'ADMIN' is created. This user has other advantage.
Admin user can search and list the order with basic authentication.Searching the order is working as partial search. 
Admin can enter client firstname and Lastname in searching bar. You can also search by city, country, post code, street,phone number and email.
Finally, you must run the project using docker-compose.yml file.
This file will have a configuration to create a RabbitMq message broker service in a Docker container and 
connect it to the container of the Spring Boot project to run as a multi-container application.


### Prerequisites
```
Jdk 11
Maven
Docker
```

### Installing
After installing necessary library, you can run the project on Command Line. 
docker-compose.yml file is responsible for running rabbitmq and spring boot project.

###### Create .jar file under the target file, Also, you can see Test results: 
```
maven clean package
```
###### Create containers and run the project as detached mode:
```
docker-compose up -d 
```
###### Stop the containers :
```
docker-compose stop 
```
###### Stop and delete images and containers :
```
docker-compose down 
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
```
http://localhost:8080/api/v1/h2-console
```

##### RabbitMQ:
```
http://localhost:15672
```




