package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractEntityCollectionMapperImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchies;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeHierarchy;

@ApplicationScoped @Deprecated
public class PrivilegeHierarchyDtoCollectionMapper extends AbstractEntityCollectionMapperImpl<PrivilegeHierarchyDtoCollection,PrivilegeHierarchyDto,PrivilegeHierarchies,PrivilegeHierarchy> implements Serializable {
	private static final long serialVersionUID = 1L;


}