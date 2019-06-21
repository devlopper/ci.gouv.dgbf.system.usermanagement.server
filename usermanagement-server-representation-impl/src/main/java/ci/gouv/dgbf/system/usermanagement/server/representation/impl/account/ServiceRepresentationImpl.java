package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.ServiceBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Service;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.ServiceRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDtoCollection;

@ApplicationScoped
public class ServiceRepresentationImpl extends AbstractRepresentationEntityImpl<Service,ServiceBusiness,ServiceDto,ServiceDtoCollection> implements ServiceRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Service> getPersistenceEntityClass() {
		return Service.class;
	}
	
}
