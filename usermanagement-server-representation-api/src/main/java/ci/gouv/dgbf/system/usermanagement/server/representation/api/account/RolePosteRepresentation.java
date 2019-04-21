package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RolePoste;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDtoCollection;

@Path(RolePosteRepresentation.PATH)
public interface RolePosteRepresentation extends RepresentationEntity<RolePoste,RolePosteDto,RolePosteDtoCollection> {
	
	String PATH = "/roleposte";
	
}
