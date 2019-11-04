package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDtoCollection;

@Path(AccountRepresentation.PATH)
public interface AccountRepresentation extends RepresentationEntity<Account, AccountDto, AccountDtoCollection> {

	@POST
	@Path(PATH_UPDATE_PASSWORD_EMAIL_BY_PRINCIPAL_NAME)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response sendUpdatePasswordEmailByPrincipalName(@QueryParam("principalName") String principalName);

	String PATH = "compte";
	
	String PATH_UPDATE_PASSWORD_EMAIL_BY_PRINCIPAL_NAME = "updatepasswordbyprincipalname";
}
