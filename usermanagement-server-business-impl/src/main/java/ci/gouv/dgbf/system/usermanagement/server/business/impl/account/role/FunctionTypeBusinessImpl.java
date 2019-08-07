package ci.gouv.dgbf.system.usermanagement.server.business.impl.account.role;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.FunctionTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;

@ApplicationScoped
public class FunctionTypeBusinessImpl extends AbstractBusinessEntityImpl<FunctionType, FunctionTypePersistence> implements FunctionTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
}
