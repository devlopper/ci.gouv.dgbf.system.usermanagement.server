package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.cyk.utility.__kernel__.klass.ClassHelper;
import org.mockserver.client.MockServerClient;
import org.mockserver.client.initialize.ExpectationInitializer;

public class MockServerExpectationInitializer implements ExpectationInitializer {

	@Override
	public void initializeExpectations(MockServerClient mockServerClient) {
		mockServerClient
		.when(request().withMethod("GET").withPath("/section"))
		.respond(response().withStatusCode(200).withBody(ClassHelper.getResourceContentAsString(MockServerExpectationInitializer.class,"section.json")));
		
		mockServerClient
		.when(request().withMethod("GET").withPath("/ugp"))
		.respond(response().withStatusCode(200).withBody(ClassHelper.getResourceContentAsString(MockServerExpectationInitializer.class,"ugp.json")));
		
		mockServerClient
		.when(request().withMethod("GET").withPath("/ua"))
		.respond(response().withStatusCode(200).withBody(ClassHelper.getResourceContentAsString(MockServerExpectationInitializer.class,"ua.json")));
	}
		
}
