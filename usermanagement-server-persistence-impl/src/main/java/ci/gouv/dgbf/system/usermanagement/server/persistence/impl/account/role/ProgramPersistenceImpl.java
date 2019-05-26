package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.persistence.AbstractPersistenceEntityIdentifiedByStringImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProgramPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;

@Singleton
public class ProgramPersistenceImpl extends AbstractPersistenceEntityIdentifiedByStringImpl<Program> implements ProgramPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	/*
	@Override
	protected Strings __getReadOneUniformResourceIdentifierFormats__() {
		//TODO should be taken from environment variables
		return __inject__(Strings.class).add("http://10.3.4.20:32202/sib/classification-par-programme/api/v1/programmes/code/%s");
	}
	*/
	
}
