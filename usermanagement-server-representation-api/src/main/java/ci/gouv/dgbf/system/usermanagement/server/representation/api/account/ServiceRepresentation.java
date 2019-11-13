package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;

@Path(ServiceRepresentation.PATH)
public interface ServiceRepresentation extends RepresentationEntity<ServiceDto> {
	
	String PATH = "/service";
	
}
