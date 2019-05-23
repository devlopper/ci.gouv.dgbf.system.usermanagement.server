package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProgramBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProgramPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;

@Singleton
public class ProgramBusinessImpl extends AbstractBusinessEntityImpl<Program, ProgramPersistence> implements ProgramBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Program> __getPersistenceEntityClass__() {
		return Program.class;
	}
	
}
