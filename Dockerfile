FROM maven:3.6.3-openjdk-11-slim AS BUILD

#copy all files and pom to the container
COPY ./ ./

RUN mvn clean package

#second stage will use open jdk 11 alpine
FROM openjdk:11.0.7-jre-slim

COPY --from=BUILD target/final-project-template-0.0.1-SNAPSHOT.jar /final-project.jar

EXPOSE 8080
#CMD ["java", "-jar", "/final-project.jar"]
ENTRYPOINT ["java", "-jar", "/final-project.jar"]

#docker build -t NAME_PROJ .
#docker run -d -p 8080:8080 final-project
