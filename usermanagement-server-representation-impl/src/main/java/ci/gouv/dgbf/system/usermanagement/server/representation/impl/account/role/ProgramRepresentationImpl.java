package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProgramBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.ProgramRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProgramDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.ProgramDtoCollection;

@Singleton
public class ProgramRepresentationImpl extends AbstractRepresentationEntityImpl<Program,ProgramBusiness,ProgramDto,ProgramDtoCollection> implements ProgramRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<Program> getPersistenceEntityClass() {
		return Program.class;
	}
	
}
