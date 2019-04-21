package ci.gouv.dgbf.system.usermanagement.server.business.impl.keycloak;

import java.io.Serializable;

import javax.inject.Singleton;

import ci.gouv.dgbf.system.usermanagement.server.business.impl.account.AbstractRoleBusinessImpl;
import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.Keycloak;

@Singleton @Keycloak
public class RoleBusinessImpl extends AbstractRoleBusinessImpl implements Serializable {
	private static final long serialVersionUID = 1L;

}
