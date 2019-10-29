package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.protocol.http.HttpHelper;
import org.cyk.utility.__kernel__.rest.RestHelper;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.test.weld.AbstractWeldUnitTest;
import org.junit.jupiter.api.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

public class EntitiesUnitTest extends AbstractWeldUnitTest {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		Object classifier = "SECTION";
		
		System.setProperty(VariableName.buildClassUniformResourceIdentifier(Scope.class,classifier), "http://localhost:10000/section");
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_CODE), "numero");
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_NAME), "libelleLong");
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_LINK), "uuid");
		
		classifier = "PROGRAM";
		
		System.setProperty(VariableName.buildClassUniformResourceIdentifier(Scope.class,classifier), "http://localhost:10000/programme");
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_CODE), "code");
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_NAME), "libelle");
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_LINK), "uuid");
	}
	
	@Override
	protected void __listenAfter__() {
		super.__listenAfter__();
		HttpHelper.clear();
	}
	
	@Test
	public void get_section() throws IOException {
		Collection<Scope> scopes = RestHelper.getMany(Scope.class, "SECTION");
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getCode).collect(Collectors.toList())).containsAnyOf("01","02");
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("PRIMATURE","MEDIATURE");
	}
	
	@Test
	public void get_program() throws IOException {
		Collection<Scope> scopes = RestHelper.getMany(Scope.class, "PROGRAM");
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getCode).collect(Collectors.toList())).containsAnyOf("01","02");
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("Lutte contre la pauvrete","Alphabetisation de la jeune fille");
	}
}
