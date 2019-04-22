package ci.gouv.dgbf.system.usermanagement.server.representation.api.account;

import javax.ws.rs.Path;

import org.cyk.utility.server.representation.RepresentationEntity;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDtoCollection;

@Path(ServiceRepresentation.PATH)
public interface ServiceRepresentation extends RepresentationEntity<Service,ServiceDto,ServiceDtoCollection> {
	
	String PATH = "/service";
	
}
