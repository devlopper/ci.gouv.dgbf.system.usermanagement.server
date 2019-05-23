package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.request.RequestProcessor;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ProgramPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;

@Singleton
public class ProgramPersistenceImpl extends AbstractPersistenceEntityImpl<Program> implements ProgramPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __processAfterRead__(Program program) {
		super.__processAfterRead__(program);
		Program remote = (Program) __inject__(RequestProcessor.class)
				.setUniformResourceIdentifierString("http://10.3.4.20:32202/sib/classification-par-programme/api/v1/programmes/code/"+program.getIdentifier())
				.setResponseEntityClass(Program.class)
				.execute().getOutput();
		program.setName(remote.getName());
	}
	
}
