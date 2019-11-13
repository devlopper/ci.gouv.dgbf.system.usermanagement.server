package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import org.cyk.utility.__kernel__.security.keycloak.KeycloakHelper;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.AccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;

@ApplicationScoped
public class AccountRepresentationImpl extends AbstractRepresentationEntityImpl<AccountDto> implements AccountRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response sendUpdatePasswordEmailByPrincipalName(String principalName) {
		KeycloakHelper.executeActionEmailUpdatePassword(principalName);
		return Response.ok().build();
	}

}
