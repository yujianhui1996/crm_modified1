from openjdk:8-jdk-alpine
ENV DUBBO_IP_TO_REGISTRY 47.102.36.182
ENV DUBBO_PORT_TO_REGISTRY 20889
ENV DUBBO_PORT_TO_BIND 20889
VOLUME /tmp
ARG JAR_FILE=fit-crm-main/target/fit-crm-main-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 20889