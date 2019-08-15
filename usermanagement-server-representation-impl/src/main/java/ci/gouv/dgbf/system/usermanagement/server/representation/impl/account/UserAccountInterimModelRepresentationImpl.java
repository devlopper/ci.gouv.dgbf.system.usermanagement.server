package ci.gouv.dgbf.system.usermanagement.server.representation.impl.account;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.UserAccountInterimModelBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccountInterimModel;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountInterimModelRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimModelDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountInterimModelDtoCollection;

@ApplicationScoped
public class UserAccountInterimModelRepresentationImpl extends AbstractRepresentationEntityImpl<UserAccountInterimModel,UserAccountInterimModelBusiness,UserAccountInterimModelDto,UserAccountInterimModelDtoCollection> implements UserAccountInterimModelRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

}
