package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDtoCollection;

@ApplicationScoped
public class UserAccountRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccount,UserAccountBusiness,UserAccountDto,UserAccountDtoCollection> implements UserAccountRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<UserAccount> getPersistenceEntityClass() {
		return UserAccount.class;
	}
	
}
