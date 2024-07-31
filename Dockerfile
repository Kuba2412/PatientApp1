FROM openjdk:21-jdk
MAINTAINER Kuba2412
COPY target/PatientApp1-0.0.1-SNAPSHOT.jar PatientApp1.jar
ENTRYPOINT ["java", "-jar", "PatientApp1.jar"]