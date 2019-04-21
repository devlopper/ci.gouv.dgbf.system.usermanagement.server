package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDtoCollection;

@Singleton
public class RoleCategoryRepresentationImpl extends AbstractRepresentationEntityImpl<RoleCategory,RoleCategoryBusiness,RoleCategoryDto,RoleCategoryDtoCollection> implements RoleCategoryRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<RoleCategory> getPersistenceEntityClass() {
		return RoleCategory.class;
	}
	
}
