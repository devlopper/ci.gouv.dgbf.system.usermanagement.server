set cyk.parameter.security.delegate.system.is.enable=true
set keycloak.server.url=http://localhost:8230/auth
set keycloak.realm.name=Test
set keycloak.client.identifier=admin-cli
set keycloak.client.secret=455b2b49-b120-4c5b-82e2-7d4d2bb388da
set keycloak.credential.username=admin
set keycloak.credential.password=admin
cd..
cd..
cd..
%java_home%/bin/java.exe -jar target/usermanagement-server-deployment-0.1.0-thorntail.jar