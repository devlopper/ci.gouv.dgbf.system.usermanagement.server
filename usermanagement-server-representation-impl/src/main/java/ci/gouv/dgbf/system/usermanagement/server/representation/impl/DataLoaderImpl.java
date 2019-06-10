package ci.gouv.dgbf.system.usermanagement.server.representation.impl;
import java.io.Serializable;

import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.impl.AbstractDataLoaderImpl;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System
public class DataLoaderImpl extends AbstractDataLoaderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Response __execute__() throws Exception {
		__inject__(ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener.class).saveDataFromResources();
		return Response.ok().build();
	}
	
}