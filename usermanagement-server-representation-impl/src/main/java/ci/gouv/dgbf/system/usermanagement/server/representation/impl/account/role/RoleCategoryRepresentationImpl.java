package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDtoCollection;

@Singleton
public class RoleCategoryRepresentationImpl extends AbstractRepresentationEntityImpl<RoleCategory,RoleCategoryBusiness,RoleCategoryDto,RoleCategoryDtoCollection> implements RoleCategoryRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleCategory> getPersistenceEntityClass() {
		return RoleCategory.class;
	}
	
}
