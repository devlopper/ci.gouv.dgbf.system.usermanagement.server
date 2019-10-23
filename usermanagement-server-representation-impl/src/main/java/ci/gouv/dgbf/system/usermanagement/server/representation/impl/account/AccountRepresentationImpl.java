package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.AccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDtoCollection;

@ApplicationScoped
public class AccountRepresentationImpl extends AbstractRepresentationEntityImpl<Account,AccountBusiness,AccountDto,AccountDtoCollection> implements AccountRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
