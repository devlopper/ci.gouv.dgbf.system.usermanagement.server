package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDtoCollection;

@Path(UserAccountRepresentation.PATH)
public interface UserAccountRepresentation extends RepresentationEntity<UserAccount,UserAccountDto,UserAccountDtoCollection> {
	
	@POST
	@Path("/__internal__/data/import")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	Response importFromKeycloak();
	
	String PATH = "compteutilisateur";
	
}
