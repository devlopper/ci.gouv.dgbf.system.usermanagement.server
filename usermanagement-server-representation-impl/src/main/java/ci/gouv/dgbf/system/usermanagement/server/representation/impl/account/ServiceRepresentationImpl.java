package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.ServiceRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.ServiceDto;

@ApplicationScoped
public class ServiceRepresentationImpl extends AbstractRepresentationEntityImpl<ServiceDto> implements ServiceRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
