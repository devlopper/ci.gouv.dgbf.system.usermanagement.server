package ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role;

import org.cyk.utility.server.persistence.jpa.hierarchy.PersistenceIdentifiedByStringAndCoded;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;

public interface PrivilegePersistence extends PersistenceIdentifiedByStringAndCoded<Privilege> {

	/**/
	
	String READ_BY_PROFILES_IDENTIFIERS = "READ_BY_PROFILES_IDENTIFIERS";
	
}
