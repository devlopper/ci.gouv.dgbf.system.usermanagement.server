package ci.gouv.dgbf.system.usermanagement.server.representation.impl.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.stream.distributed.Topic;
import org.cyk.utility.time.TimeHelper;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.persistence.impl.keycloak.KeycloakHelper;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public class RepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();
		__inject__(KeycloakHelper.class).load();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Test
	public void read_roleCategory() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleCategoryDto> roleCategoryDtos = (Collection<RoleCategoryDto>) __inject__(RoleCategoryRepresentation.class).getMany(0l, 1000l, null,null).getEntity();
		assertThat(roleCategoryDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	@Test
	public void read_roleFunction() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleFunctionDto> roleFunctionDtos = (Collection<RoleFunctionDto>) __inject__(RoleFunctionRepresentation.class).getMany(0l, 1000l, null,null).getEntity();
		assertThat(roleFunctionDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ASSISTANT","DIRECTEUR");
	}
	
	@Test
	public void read_rolePoste() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RolePosteDto> rolePosteDtos = (Collection<RolePosteDto>) __inject__(RolePosteRepresentation.class).getMany(0l, 1000l, null,null).getEntity();
		assertThat(rolePosteDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21","CONTROLEUR_FINANCIER_MINISTERE_21");
	}
	
	@Test
	public void create_userAccount() throws Exception{
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			startServersZookeeperAndKafka();
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress("kycdev@gmail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostes("CE");
		__inject__(TestRepresentationCreate.class).addObjects(userAccount).setIsCatchThrowable(Boolean.FALSE).execute();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
