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

import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.UserAccount;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.UserAccountRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RoleCategoryRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RoleFunctionRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.api.account.role.RolePosteRepresentation;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.UserAccountDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleCategoryDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RoleFunctionDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.entities.account.role.RolePosteDto;
import ci.gouv.dgbf.system.usermanagement.server.representation.impl.ApplicationScopeLifeCycleListener;

public class RepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Test
	public void read_roleCategory() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleCategoryDto> roleCategoryDtos = (Collection<RoleCategoryDto>) __inject__(RoleCategoryRepresentation.class).getMany(Boolean.FALSE,null, null, null,null).getEntity();
		assertThat(roleCategoryDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ADMINISTRATIF","BUDGETAIRE");
	}
	
	@Test
	public void count_roleCategory() throws Exception{
		Long count = (Long) __inject__(RoleCategoryRepresentation.class).count(null).getEntity();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	public void read_roleFunction() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RoleFunctionDto> roleFunctionDtos = (Collection<RoleFunctionDto>) __inject__(RoleFunctionRepresentation.class).getMany(Boolean.FALSE,null, null, null,null).getEntity();
		assertThat(roleFunctionDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ASSISTANT","DIRECTEUR");
	}
	
	@Test
	public void read_rolePoste() throws Exception{
		@SuppressWarnings("unchecked")
		Collection<RolePosteDto> rolePosteDtos = (Collection<RolePosteDto>) __inject__(RolePosteRepresentation.class).getMany(Boolean.FALSE,null, null, null,null).getEntity();
		assertThat(rolePosteDtos.stream().map(x -> x.getCode()).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21","CONTROLEUR_FINANCIER_MINISTERE_21");
	}
	
	@Test
	public void create_userAccount() throws Exception{
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			startServersZookeeperAndKafka();
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 15);
		}
		
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomCode__()+"@gmail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostesByCodes("ASSISTANT_SAISIE_MINISTERE_21");
		__inject__(TestRepresentationCreate.class).addObjects(userAccount).setIsCatchThrowable(Boolean.FALSE).addTryEndRunnables(new Runnable() {
			@Override
			public void run() {
				UserAccountDto userAccount01 = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null, null).getEntity();
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getRolePostes()).isNull();
				
				userAccount01 = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(),null,UserAccount.FIELD_ROLE_POSTES).getEntity();
				assertThat(userAccount01).isNotNull();
				assertThat(userAccount01.getRolePostes()).isNotNull();
				assertThat(userAccount01.getRolePostes().getCollection()).isNotEmpty();
				assertThat(userAccount01.getRolePostes().getCollection().stream().map(RolePosteDto::getCode).collect(Collectors.toList())).contains("ASSISTANT_SAISIE_MINISTERE_21");
			}
		}).execute();
		
		if(Boolean.TRUE.equals(Topic.MAIL.getIsConsumerStarted())) {
			__inject__(TimeHelper.class).pause(1000l * 25);
			stopServersKafkaAndZookeeper();	
		}
	}
	
	@Test
	public void update_userAccount() throws Exception{
		UserAccountDto userAccount = new UserAccountDto();
		userAccount.getUser(Boolean.TRUE).setFirstName("Zadi").setLastNames("Paul-François").setElectronicMailAddress(__getRandomCode__()+"@mail.com");
		userAccount.getAccount(Boolean.TRUE).setIdentifier(__getRandomCode__()).setPass("123");
		userAccount.addRolePostesByCodes("CONTROLEUR_FINANCIER_MINISTERE_21");
		
		__inject__(UserAccountRepresentation.class).createOne(userAccount);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,UserAccount.FIELD_ROLE_POSTES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().getCollection()).isNotEmpty();
		assertThat(userAccount.getRolePostes().getCollection().stream().map(RolePosteDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21");
		
		userAccount.addRolePostesByCodes("ASSISTANT_SAISIE_MINISTERE_21");
		__inject__(UserAccountRepresentation.class).updateOne(userAccount,UserAccountDto.FIELD_ROLE_POSTES);
		
		userAccount = (UserAccountDto) __inject__(UserAccountRepresentation.class).getOne(userAccount.getIdentifier(), null,UserAccount.FIELD_ROLE_POSTES).getEntity();
		assertThat(userAccount).isNotNull();
		assertThat(userAccount.getRolePostes()).isNotNull();
		assertThat(userAccount.getRolePostes().getCollection()).isNotEmpty();
		assertThat(userAccount.getRolePostes().getCollection().stream().map(RolePosteDto::getCode).collect(Collectors.toList())).contains("CONTROLEUR_FINANCIER_MINISTERE_21"
				,"ASSISTANT_SAISIE_MINISTERE_21");
		
		__inject__(UserAccountRepresentation.class).deleteOne(userAccount);
	}
	
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		return null;
	}
	

}
