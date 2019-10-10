FROM adoptopenjdk/openjdk11-openj9:jre-11.0.3_7_openj9-0.14.0-alpine
ADD target/usermanagement-server-deployment-0.1.0-thorntail.jar /opt/runnable.jar
EXPOSE 8080
# Default Environment variables
ENV cyk.parameter.security.delegate.system.is.enable true
ENV keycloak.client.identifier admin-cli
ENV keycloak.client.secret b235602b-e5c9-4ddb-bfe5-aaaf79dacb71
ENV keycloak.credential.password admin
ENV keycloak.credential.username admin
ENV keycloak.realm.name SIB
ENV keycloak.server.url http://10.3.4.20:31000/auth
ENTRYPOINT ["java", "-jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv4Addresses=true", "-XX:MaxRAM=1G","/opt/runnable.jar"]