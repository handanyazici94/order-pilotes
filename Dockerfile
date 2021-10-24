#FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]


FROM openjdk:11
EXPOSE 8080
ADD target/backend-technical-test.jar backend-technical-test.jar
##########
#RUN mvn package -Dmaven.test.skip=false
ENTRYPOINT ["java","-jar","/backend-technical-test.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]


#######
#FROM openjdk:11 as base
#WORKDIR /backend-technical-test-v2

#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY src ./src

#FROM  base as test
#RUN ["./mvnw", "test"]

#FROM base as development
#CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]

#FROM base as build
#RUN ./mvnw package

#FROM openjdk:11-jre-slim as production
#EXPOSE 8080

#COPY --from=build /target/backend-technical-test.jar-*.jar /backend-technical-test.jar

#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/backend-technical-test.jar"]
############







