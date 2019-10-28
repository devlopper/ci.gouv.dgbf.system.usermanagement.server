package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.cyk.utility.__kernel__.rest.RestHelper;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.test.weld.AbstractWeldUnitTest;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

public class EntitiesUnitTestIntegration extends AbstractWeldUnitTest {
	private static final long serialVersionUID = 1L;

	private ClientAndServer server;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		server =  ClientAndServer.startClientAndServer(10000);
	}
	
	@Override
	protected void __listenAfter__() {
		super.__listenAfter__();
		server.stop();
	}
	
	@SuppressWarnings("resource")
	@Test
	public void get() throws IOException {
		new MockServerClient("localhost", 10000)
	    .when(HttpRequest.request().withMethod("GET").withPath("/section"))
	    .respond(org.mockserver.model.HttpResponse.response().withStatusCode(200).withBody(IOUtils.toString(EntitiesUnitTestIntegration.class.getResource("section.json"), Charset.forName("UTF-8"))));
		
		System.setProperty(VariableName.buildClassUniformResourceIdentifier(Scope.class), "http://localhost:10000/section");
		System.setProperty(VariableName.buildFieldName(Scope.class, "identifier"), "numero");
		System.setProperty(VariableName.buildFieldName(Scope.class, "name"), "libelleLong");
		System.setProperty(VariableName.buildFieldName(Scope.class, "link"), "uuid");
		
		Collection<Scope> scopes = RestHelper.getMany(Scope.class);
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getIdentifier).collect(Collectors.toList())).containsAnyOf("01","02");
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("PRIMATURE","MEDIATURE");
	}

}
