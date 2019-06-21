package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDtoCollection;

@Path(UserAccountRepresentation.PATH)
public interface UserAccountRepresentation extends RepresentationEntity<UserAccount,UserAccountDto,UserAccountDtoCollection> {
	
	String PATH = "/compteutilisateur";
	
}
