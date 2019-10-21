package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import java.util.Collection;

import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.test.weld.AbstractWeldUnitTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;

@Disabled
public class EntitiesUnitTestIntegration extends AbstractWeldUnitTest {
	private static final long serialVersionUID = 1L;

	@Test
	public void get() {
		ConfigurationHelper.setClassUniformResourceIdentifier(Scope.class, "http://localhost:3000/sections");
		ConfigurationHelper.setFieldName(Scope.class, "code", "identifier");
		ConfigurationHelper.setFieldName(Scope.class, "libelle", "name");
		ConfigurationHelper.setFieldName(Scope.class, "uuid", "link");
		//String url = "http://10.3.4.20:32201/sib/classification-administrative/api/v1/sections-budgetaires";		
		Collection<Scope> scopes = InstanceGetter.getInstance().getFromUniformResourceIdentifier(Scope.class,"code","libelle","uuid");
		for(Scope index : scopes)
			System.out.println(index.getIdentifier()+" / "+index.getName()+" / "+index.getLink());
		
	}

}
