#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]


FROM openjdk:11
EXPOSE 8080
ADD target/backend-technical-test.jar backend-technical-test.jar
ENTRYPOINT ["java","-jar","/backend-technical-test.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]