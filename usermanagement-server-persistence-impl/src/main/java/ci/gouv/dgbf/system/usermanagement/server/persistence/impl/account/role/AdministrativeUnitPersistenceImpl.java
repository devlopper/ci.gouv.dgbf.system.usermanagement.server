package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.AdministrativeUnitPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;

@Singleton
public class AdministrativeUnitPersistenceImpl extends AbstractPersistenceEntityIdentifiedByStringImpl<AdministrativeUnit> implements AdministrativeUnitPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	/*
	@Override
	protected Strings __getReadOneUniformResourceIdentifierFormats__() {
		//TODO should be taken from environment variables
		return __inject__(Strings.class).add("http://10.3.4.20:32201/sib/classification-administrative/api/v1/unites-administratives/code/%s");
	}
	*/
}
