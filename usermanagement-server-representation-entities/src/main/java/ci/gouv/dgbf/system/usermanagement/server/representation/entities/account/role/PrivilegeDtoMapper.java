package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import org.cyk.utility.server.representation.hierarchy.AbstractNodeMapperImpl;
import org.mapstruct.Mapper;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privileges;

@Mapper
public abstract class PrivilegeDtoMapper extends AbstractNodeMapperImpl<PrivilegeDto, Privilege,PrivilegeDtoCollection,Privileges> {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected Class<PrivilegeDtoCollection> __getSourceCollectionClass__() {
		return PrivilegeDtoCollection.class;
	}
	
	@Override
	protected Class<Privileges> __getDestinationCollectionClass__() {
		return Privileges.class;
	}
	
	@Override
	protected Class<Privilege> __getDestinationClass__() {
		return Privilege.class;
	}
 
}