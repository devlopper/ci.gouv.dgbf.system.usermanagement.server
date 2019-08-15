package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountInterimBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterim;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountInterimRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimDtoCollection;

@ApplicationScoped
public class UserAccountInterimRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountInterim,UserAccountInterimBusiness,UserAccountInterimDto,UserAccountInterimDtoCollection> implements UserAccountInterimRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
