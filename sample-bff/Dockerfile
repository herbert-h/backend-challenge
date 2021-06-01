FROM openjdk:11-jre-slim-sid

WORKDIR /usr/src/app

COPY webservice/build/libs/sample-bff*.jar sample-bff-webservice.jar

ENTRYPOINT ["java", "-jar", "sample-bff-webservice.jar"]
