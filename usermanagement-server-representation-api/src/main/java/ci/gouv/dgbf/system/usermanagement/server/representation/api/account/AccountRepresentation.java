package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDtoCollection;

@Path(AccountRepresentation.PATH)
public interface AccountRepresentation extends RepresentationEntity<Account,AccountDto,AccountDtoCollection> {
	
	String PATH = "/account";
	
	@GET
	@Path(PATH_AUTHENTICATE)
	@Consumes(MediaType.APPLICATION_XML)
	/**
	 * Authenticate an account. If code is known then all related profile are loaded
	 * @param account
	 * @return
	 */
	Response authenticate(AccountDto account);
	
	/**/

	String PATH_AUTHENTICATE = "authenticate";
}
