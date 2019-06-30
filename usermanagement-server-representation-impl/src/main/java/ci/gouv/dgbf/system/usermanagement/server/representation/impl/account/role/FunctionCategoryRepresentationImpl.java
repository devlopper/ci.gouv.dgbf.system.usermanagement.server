package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.FunctionCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.FunctionCategoryDtoCollection;

@ApplicationScoped
public class FunctionCategoryRepresentationImpl extends AbstractRepresentationEntityImpl<FunctionCategory,FunctionCategoryBusiness,FunctionCategoryDto,FunctionCategoryDtoCollection> implements FunctionCategoryRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<FunctionCategory> getPersistenceEntityClass() {
		return FunctionCategory.class;
	}
	
}
