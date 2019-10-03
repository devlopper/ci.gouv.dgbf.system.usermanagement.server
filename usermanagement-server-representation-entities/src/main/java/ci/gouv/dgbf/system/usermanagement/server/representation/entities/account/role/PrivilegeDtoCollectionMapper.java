package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractEntityCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privileges;

@ApplicationScoped @Deprecated
public class PrivilegeDtoCollectionMapper extends AbstractEntityCollectionMapperImpl<PrivilegeDtoCollection,PrivilegeDto,Privileges,Privilege> implements Serializable {
	private static final long serialVersionUID = 1L;


}