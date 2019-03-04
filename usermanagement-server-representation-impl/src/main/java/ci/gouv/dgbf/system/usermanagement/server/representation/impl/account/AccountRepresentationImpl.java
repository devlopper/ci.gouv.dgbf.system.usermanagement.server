package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;
import org.cyk.utility.server.representation.ResponseBuilder;
import org.cyk.utility.server.representation.ResponseEntityDtoBuilder;
import org.cyk.utility.system.action.SystemActionAuthenticate;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.AccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDtoCollection;

@Singleton
public class AccountRepresentationImpl extends AbstractRepresentationEntityImpl<Account,AccountBusiness,AccountDto,AccountDtoCollection> implements AccountRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response authenticate(AccountDto account) {
		Response response = null;
		try {
			Account persistence = getBusiness().authenticate(account.getCode(), account.getPass());
			account = __injectInstanceHelper__().buildOne(AccountDto.class, persistence);
			response = Response.ok(account).build();
		} catch (Exception throwable) {
			response = __inject__(ResponseBuilder.class).setResponseEntityDto(__inject__(ResponseEntityDtoBuilder.class)
					.setSystemAction(__inject__(SystemActionAuthenticate.class))
					.setThrowable(throwable)).execute().getOutput();
		}
		return response;
	}
	
	@Override
	public Class<Account> getPersistenceEntityClass() {
		return Account.class;
	}

}
