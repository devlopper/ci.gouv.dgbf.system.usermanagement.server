package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ScopeTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ScopeTypeDtoCollection;

@ApplicationScoped
public class ScopeTypeRepresentationImpl extends AbstractRepresentationEntityImpl<ScopeType,ScopeTypeBusiness,ScopeTypeDto,ScopeTypeDtoCollection> implements ScopeTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
