package ci.gouv.dgbf.system.usermanagement.server.representation.impl;
import java.io.Serializable;

import javax.ws.rs.core.Response;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System
public class DataLoaderImpl extends org.cyk.utility.server.representation.AbstractDataLoaderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response load() {
		__inject__(ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener.class).saveDataFromResources();
		return Response.ok().build();
	}
	
}