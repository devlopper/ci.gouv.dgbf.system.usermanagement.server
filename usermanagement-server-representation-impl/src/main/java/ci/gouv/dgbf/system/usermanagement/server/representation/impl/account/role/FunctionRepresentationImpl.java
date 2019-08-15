package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionDtoCollection;

@ApplicationScoped
public class FunctionRepresentationImpl extends AbstractRepresentationEntityImpl<Function,FunctionBusiness,FunctionDto,FunctionDtoCollection> implements FunctionRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
