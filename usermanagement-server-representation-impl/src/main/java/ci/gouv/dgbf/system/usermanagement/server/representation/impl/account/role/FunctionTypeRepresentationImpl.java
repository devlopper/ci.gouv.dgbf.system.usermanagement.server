package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionTypeRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionTypeDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionTypeDtoCollection;

@ApplicationScoped
public class FunctionTypeRepresentationImpl extends AbstractRepresentationEntityImpl<FunctionType,FunctionTypeBusiness,FunctionTypeDto,FunctionTypeDtoCollection> implements FunctionTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
