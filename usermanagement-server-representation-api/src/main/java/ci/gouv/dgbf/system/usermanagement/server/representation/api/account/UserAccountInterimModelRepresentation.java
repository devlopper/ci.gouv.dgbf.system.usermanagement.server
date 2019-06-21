package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimModelDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimModelDtoCollection;

@Path(UserAccountInterimModelRepresentation.PATH)
public interface UserAccountInterimModelRepresentation extends RepresentationEntity<UserAccountInterimModel,UserAccountInterimModelDto,UserAccountInterimModelDtoCollection> {
	
	String PATH = "/interimaire";
	
}
