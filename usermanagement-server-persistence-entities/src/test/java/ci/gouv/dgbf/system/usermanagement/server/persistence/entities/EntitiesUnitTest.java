package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.cyk.utility.request.RequestProcessor;
import org.cyk.utility.test.arquillian.AbstractArquillianUnitTest;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;

public class EntitiesUnitTest extends AbstractArquillianUnitTest {
	private static final long serialVersionUID = 1L;

	@Test
	public void get_program() {
		Program dto = (Program) __inject__(RequestProcessor.class)
				.setUniformResourceIdentifierString("http://10.3.4.20:32202/sib/classification-par-programme/api/v1/programmes/code/22060")
				.setResponseEntityClass(Program.class)
				.execute().getOutput();
		assertThat(dto.getLink()).isEqualTo("2521444c-0058-4ab7-b18e-c5d27f785da3");
		assertThat(dto.getName()).isEqualTo("TRANSPORT TERRESTRE");
	}
	
	@Test
	public void get_administrativeUnit() {
		AdministrativeUnit dto = (AdministrativeUnit) __inject__(RequestProcessor.class)
				.setUniformResourceIdentifierString("http://10.3.4.20:32201/sib/classification-administrative/api/v1/unites-administratives/code/9918390")
				.setResponseEntityClass(AdministrativeUnit.class)
				.execute().getOutput();
		assertThat(dto.getLink()).isEqualTo("c7be3444-1b48-43aa-8965-fc45a811f3d3");
		assertThat(dto.getName()).isEqualTo("Renforcement des Capacités Open Government Partnership (OGP)");
	}
	
	/**/
	
	@org.jboss.arquillian.container.test.api.Deployment
	public static org.jboss.shrinkwrap.api.spec.JavaArchive createDeployment() {
		return new org.cyk.utility.__kernel__.test.arquillian.archive.builder.JavaArchiveBuilder()
				.addPackage(org.cyk.utility.ApplicationScopeLifeCycleListener.class.getPackage().getName(),Boolean.TRUE)
				.addPackage(Program.class.getPackage().getName(),Boolean.TRUE)
				.execute();
	}

}
