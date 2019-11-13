package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountProfileDto;

@Path(UserAccountProfileRepresentation.PATH)
public interface UserAccountProfileRepresentation extends RepresentationEntity<UserAccountProfileDto> {
	
	String PATH = "/compteutilisateurprofile";
	
}
