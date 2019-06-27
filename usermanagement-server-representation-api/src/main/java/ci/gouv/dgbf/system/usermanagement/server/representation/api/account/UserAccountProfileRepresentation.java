package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountProfile;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDtoCollection;

@Path(UserAccountProfileRepresentation.PATH)
public interface UserAccountProfileRepresentation extends RepresentationEntity<UserAccountProfile,UserAccountProfileDto,UserAccountProfileDtoCollection> {
	
	String PATH = "/compteutilisateurprofile";
	
}
