package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.test.arquillian.archive.builder.WebArchiveBuilder;
import org.cyk.utility.__kernel__.variable.VariableHelper;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTest;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;

public class PersistenceIntegrationTestPerformance extends AbstractPersistenceArquillianIntegrationTest {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		ConfigurationHelper.clear();
	}
	
	@Test
	public void read_scope_many() throws Exception{
		VariableHelper.writeClassUniformResourceIdentifier(Scope.class,"SECTION", getClass().getResource("section.json"));
		VariableHelper.writeFieldName(Scope.class,"SECTION", "code", "code");
		VariableHelper.writeFieldName(Scope.class,"SECTION", "libelle", "name");
		VariableHelper.writeFieldName(Scope.class,"SECTION", "uuid", "link");
		VariableHelper.writeClassUniformResourceIdentifier(Scope.class,"PROGRAM", getClass().getResource("program.json"));
		VariableHelper.writeFieldName(Scope.class,"PROGRAM", "code", "code");
		VariableHelper.writeFieldName(Scope.class,"PROGRAM", "libelle", "name");
		VariableHelper.writeFieldName(Scope.class,"PROGRAM", "uuid", "link");
		
		userTransaction.begin();
		ScopeType scopeType = new ScopeType().setCode("SECTION").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("01"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("02"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("03"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("04"));
		/*scopeType = new ScopeType().setCode("PROGRAM").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("221"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("2"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("3"));
		*/
		userTransaction.commit();
		Collection<Scope> scopes = __inject__(ScopePersistence.class).read();
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("Ministère de la Justice","Ministère des infrastructures"
				,"Ministère de la défense","Ministère du Budget","Lutte contre la pauvreté","Alphabétisation de la jeune fille","Sécurité et défense de la nation");
	}
	
	//@Test
	public void read_scope_one() throws Exception{
		VariableHelper.writeClassUniformResourceIdentifier(Scope.class,"SECTION", getClass().getResource("section.json"));
		VariableHelper.writeFieldName(Scope.class,"SECTION", "code", "code");
		VariableHelper.writeFieldName(Scope.class,"SECTION", "libelle", "name");
		VariableHelper.writeFieldName(Scope.class,"SECTION", "uuid", "link");
		VariableHelper.writeClassUniformResourceIdentifier(Scope.class,"PROGRAM", getClass().getResource("program.json"));
		VariableHelper.writeFieldName(Scope.class,"PROGRAM", "code", "code");
		VariableHelper.writeFieldName(Scope.class,"PROGRAM", "libelle", "name");
		VariableHelper.writeFieldName(Scope.class,"PROGRAM", "uuid", "link");
		
		userTransaction.begin();
		ScopeType scopeType = new ScopeType().setCode("SECTION").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("221"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("240"));
		__inject__(ScopePersistence.class).create(new Scope().setIdentifier("250").setType(scopeType).setCode("250"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("300"));
		scopeType = new ScopeType().setCode("PROGRAM").setName(__getRandomName__());
		__inject__(ScopeTypePersistence.class).create(scopeType);
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("221"));
		__inject__(ScopePersistence.class).create(new Scope().setIdentifier("2").setType(scopeType).setCode("2"));
		__inject__(ScopePersistence.class).create(new Scope().setType(scopeType).setCode("3"));
		userTransaction.commit();
		Scope scope = __inject__(ScopePersistence.class).readBySystemIdentifier("250");
		assertThat(scope).isNotNull();
		assertThat(scope.getName()).isEqualTo("Ministère de la défense");
		
		scope = __inject__(ScopePersistence.class).readBySystemIdentifier("2");
		assertThat(scope).isNotNull();
		assertThat(scope.getName()).isEqualTo("Alphabétisation de la jeune fille");
	}
	
	@org.jboss.arquillian.container.test.api.Deployment
	public static WebArchive createArchive(){
		return new WebArchiveBuilder().execute()
				.addAsResource("ci/gouv/dgbf/system/usermanagement/server/persistence/impl/integration/section.json")
				.addAsResource("ci/gouv/dgbf/system/usermanagement/server/persistence/impl/integration/program.json")
				; 
	}
}
