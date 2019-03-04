package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import javax.ws.rs.core.Response;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.AccountBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.Account;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.AccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.AccountDto;

public class AccountFunctionIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void createOneAccount() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		AccountDto account = new AccountDto().setCode(code).setPass(pass);
		__inject__(TestRepresentationCreate.class).addObjects(account).execute();
	}
	
	@Test
	public void authenticateAccount_success() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		__inject__(AccountBusiness.class).create(new Account().setCode(code).setPass(pass));
		Response response = __inject__(AccountRepresentation.class).authenticate(new AccountDto().setCode(code).setPass(pass));
		assertionHelper.assertEquals(200, response.getStatus());
		AccountDto accountDto = (AccountDto) response.getEntity();
		assertionHelper.assertNotNull("account with code<<"+code+">> and pass <<"+pass+">> not found",accountDto);
		assertionHelper.assertEquals(code, accountDto.getCode());
		assertionHelper.assertNull("account pass has been transfered",accountDto.getPass());
	}
	
	@Test
	public void authenticateAccount_error_code_or_pass_uncorrect() throws Exception{
		String code = __getRandomCode__();
		String pass = __getRandomString__();
		Response response = __inject__(AccountRepresentation.class).authenticate(new AccountDto().setCode(code).setPass(pass));
		assertionHelper.assertEquals(500, response.getStatus());
		//ResponseEntityDto responseEntityDto = (ResponseEntityDto) response.getEntity();
		//assertionHelper.assertNull("account with code<<"+code+">> and pass <<"+pass+">> found",responseEntityDto);
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
