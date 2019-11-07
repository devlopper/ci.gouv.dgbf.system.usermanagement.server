package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
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
		setProperties(Scope.class, "SECTION", "numero", "libelleLong", "uuid");
		setProperties(Scope.class, "UGP", "code", "libelleLong", "uuid");
		setProperties(Scope.class, "UA", "codeUA", "libelleLong", "uuid");
	}
	
	@Override
	protected void __listenAfter__() {
		super.__listenAfter__();
		HttpHelper.clear();
		ConfigurationHelper.clear();
	}
	
	@Test
	public void get_section() throws IOException {
		Collection<Scope> scopes = RestHelper.getMany(Scope.class, "SECTION");
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getCode).collect(Collectors.toList())).containsAnyOf("01","02");
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("PRIMATURE","MEDIATURE");
	}
	
	@Test
	public void get_ugp() throws IOException {
		Collection<Scope> scopes = RestHelper.getMany(Scope.class, "UGP");
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getCode).collect(Collectors.toList())).containsAnyOf("220650000","220660000");
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("ECONOMIE NUMERIQUE ET POSTE","PROMOTION DE LA JEUNESSE ");
	}
	
	@Test
	public void get_ua() throws IOException {
		Collection<Scope> scopes = RestHelper.getMany(Scope.class, "UA");
		assertThat(scopes).isNotNull();
		assertThat(scopes.stream().map(Scope::getCode).collect(Collectors.toList())).containsAnyOf("011111010002","011212010001");
		assertThat(scopes.stream().map(Scope::getName).collect(Collectors.toList())).containsAnyOf("Direction du Budget de l'Etat","Bureau principal");
	}
	
	/**/
	
	private void setProperties(Class<?> klass,Object classifier,String code,String name,String link) {		
		System.setProperty(VariableName.buildClassUniformResourceIdentifier(klass,classifier), "http://localhost:10000/"+classifier.toString().toLowerCase());
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_CODE), code);
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_NAME), name);
		System.setProperty(VariableName.buildFieldName(Scope.class, classifier, Scope.FIELD_LINK), link);
	}
}
