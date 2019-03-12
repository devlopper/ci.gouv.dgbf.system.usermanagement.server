package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.helper.AbstractHelper;
import org.cyk.utility.system.SystemHelper;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

@Singleton
public class KeycloakHelperImpl extends AbstractHelper implements KeycloakHelper,Serializable {
	private static final long serialVersionUID = 1L;

	private Keycloak client;
	
	@Override
	public Keycloak getClient() {
		if(client == null) {
			String url = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.server.url");
			String realm = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.realm.name");
			String clientIdentifier = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.client.identifier");
			String clientSecret = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.client.secret");
			String username = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.credential.username");
			String password = __inject__(SystemHelper.class).getPropertyThrowIfBlank("keycloak.credential.password");
			
			System.out.println("KEYCLOAK CLIENT TO BE CREATED\nurl:"+url+"\nrealm:"+realm+"\nclient identifier:"+clientIdentifier+"\nclient secret:"+clientSecret
					+"\nusername:"+username+"\npassword:"+password);
			
			client = KeycloakBuilder.builder().serverUrl(url).realm(realm).grantType(OAuth2Constants.PASSWORD).clientId(clientIdentifier) //
					.clientSecret(clientSecret).username(username).password(password).build();
		}
		return client;
	}

	@Override
	public KeycloakHelper setClient(Keycloak client) {
		this.client = client;
		return this;
	}
	
}
