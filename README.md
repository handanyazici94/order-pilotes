#Handan Yayla

## Summary
TUI DX Backend technical Test v2
This project is a Spring Boot project with maven package management. REST API was developed
to order.H2 was used for database management. Also, there is docker-compose.yml for running and building multiple container.

### Prerequisites
```
Jdk 11
Maven
Docker
```

### Installing
After installing necessary library, you can run the project on Command Line.
###### Create java jar under the target file: 
```
maven clean package
```
###### Create images:
```
docker-compose build 
```
###### Create containers and run the project:
```
docker-compose up -d 
```
###### Stop and delete images and containers :
```
docker-compose down 
```
docker-compose.yml file is responsible for running rabbitmq and spring boot project.

### Project Information
The project is available at port **8080**. If you want to reach the service, you must 
write **localhost:8080/api/v1**


