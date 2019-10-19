package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import org.apache.commons.io.IOUtils;
import org.cyk.utility.field.FieldValueCopy;
import org.cyk.utility.request.RequestProcessor;
import org.cyk.utility.test.weld.AbstractWeldUnitTest;
import org.junit.jupiter.api.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

public class EntitiesUnitTestIntegration extends AbstractWeldUnitTest {
	private static final long serialVersionUID = 1L;

	@Test
	public void get() {
		//String url = "http://10.3.4.20:32201/sib/classification-administrative/api/v1/sections-budgetaires";
		String url = "http://localhost:3000/sections";
		try {
			List<Scope> scopes = JsonbBuilder.create().fromJson(new URL(url).openStream()
					, new ArrayList<Scope>() {}.getClass().getGenericSuperclass());
			for(Scope index : scopes)
				System.out.println(index.getIdentifier()+" / "+index.getName()+" / "+index.getLink());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void get_program() {
		Scope dto = (Scope) __inject__(RequestProcessor.class)
				.setUniformResourceIdentifierString("http://10.3.4.20:32202/sib/classification-par-programme/api/v1/programmes/code/22060")
				.setResponseEntityClass(Scope.class)
				.execute().getOutput();
		assertThat(dto.getLink()).isEqualTo("2521444c-0058-4ab7-b18e-c5d27f785da3");
		assertThat(dto.getName()).isEqualTo("TRANSPORT TERRESTRE");
		Scope scope = new Scope().setIdentifier("myid");
		__inject__(FieldValueCopy.class).setSource(dto).setDestination(scope).setIsOverridable(Boolean.FALSE).execute();
		assertThat(scope.getIdentifier()).isEqualTo("myid");
		assertThat(scope.getLink()).isEqualTo("2521444c-0058-4ab7-b18e-c5d27f785da3");
		assertThat(scope.getName()).isEqualTo("TRANSPORT TERRESTRE");
	}
	
	//@Test
	public void get_administrativeUnit() {
		Scope dto = (Scope) __inject__(RequestProcessor.class)
				.setUniformResourceIdentifierString("http://10.3.4.20:32201/sib/classification-administrative/api/v1/unites-administratives/code/9918390")
				.setResponseEntityClass(Scope.class)
				.execute().getOutput();
		assertThat(dto.getLink()).isEqualTo("c7be3444-1b48-43aa-8965-fc45a811f3d3");
		assertThat(dto.getName()).isEqualTo("Renforcement des Capacités Open Government Partnership (OGP)");
	}
	
	/**/
	
	@org.jboss.arquillian.container.test.api.Deployment
	public static org.jboss.shrinkwrap.api.spec.JavaArchive createDeployment() {
		return new org.cyk.utility.__kernel__.test.arquillian.archive.builder.JavaArchiveBuilder()
				.addPackage(org.cyk.utility.ApplicationScopeLifeCycleListener.class.getPackage().getName(),Boolean.TRUE)
				.addPackage(Scope.class.getPackage().getName(),Boolean.TRUE)
				.execute();
	}

}
