FROM openjdk:21-slim
COPY mvnw /
COPY src/ /src
COPY .mvn/ /.mvn
COPY pom.xml /
RUN ./mvnw clean package -D skipTests


FROM openjdk:21-slim
MAINTAINER Regis Martiny
COPY --from=0 /target/desafio-tecnico-sicredi-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java","-jar","/desafio-tecnico-sicredi-0.0.1-SNAPSHOT.jar"]