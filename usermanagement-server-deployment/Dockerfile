FROM adoptopenjdk/openjdk11-openj9:jre-11.0.3_7_openj9-0.14.0-alpine
ADD target/usermanagement-server-deployment-0.0.1-thorntail.jar /opt/runnable.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv4Addresses=true", "-XX:MaxRAM=1G","/opt/runnable.jar"]