package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountRolePoste;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountRolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountRolePosteDtoCollection;

@Path(UserAccountRolePosteRepresentation.PATH)
public interface UserAccountRolePosteRepresentation extends RepresentationEntity<UserAccountRolePoste,UserAccountRolePosteDto,UserAccountRolePosteDtoCollection> {
	
	String PATH = "/compteutilisateurposte";
	
}
