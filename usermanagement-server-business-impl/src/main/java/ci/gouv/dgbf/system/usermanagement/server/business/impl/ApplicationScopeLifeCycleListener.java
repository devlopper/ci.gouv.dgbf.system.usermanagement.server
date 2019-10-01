package ci.gouv.dgbf.system.usermanagement.server.business.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.array.ArrayInstanceTwoDimensionString;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.file.excel.FileExcelSheetDataArrayReader;
import org.cyk.utility.server.business.AbstractApplicationScopeLifeCycleListenerImplementation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PrivilegeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProfileTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.PrivilegeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.ScopeTypePersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Privilege;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PrivilegeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ProfileType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListenerImplementation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void __initialize__(Object object) {
		__inject__(ci.gouv.dgbf.system.usermanagement.server.persistence.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	/*@Override
	public void __initialize__(Object object) {
		super.__initialize__(object);
		//__inject__(SystemNodeServer.class).setName("Gestion des utilisateurs");
		
		//__inject__(ProtocolDefaults.class).getSimpleMailTransfer().setHost("smtp.gmail.com").setPort(587).setIsAuthenticationRequired(Boolean.TRUE)
		//.setIsSecuredConnectionRequired(Boolean.TRUE).setAuthenticationCredentials(__inject__(Credentials.class).setIdentifier("dgbfdtideveloppers").setSecret("dgbf2016dti"));
		
		
		
		//__initializePersistenceData__();
	}*/
	
	@Override
	public void __destroy__(Object object) {}
	
	/**/
	
	public void saveDataFromResources() {
		FileExcelSheetDataArrayReader reader;
		ArrayInstanceTwoDimensionString arrayInstance;
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("TypeScope");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<ScopeType> scopeTypes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			ScopeType scopeType = new ScopeType().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1));
			for(ScopeType parent : scopeTypes)
				if(parent.getCode().equals(arrayInstance.get(index, 2))) {
					scopeType.addParents(parent);
					break;
				}
			scopeTypes.add(scopeType);
		}
		__logInfo__("Creating "+scopeTypes.size()+" type de domaine");
		__inject__(ScopeTypeBusiness.class).saveByBatch(scopeTypes,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("TypeProfile");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<ProfileType> profileTypes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			profileTypes.add(new ProfileType().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1)));
		__logInfo__("Creating "+profileTypes.size()+" type de profile");
		__inject__(ProfileTypeBusiness.class).saveByBatch(profileTypes,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("TypePrivilege");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<PrivilegeType> privilegeTypes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			PrivilegeType privilegeType = new PrivilegeType().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1));
			for(PrivilegeType parent : privilegeTypes)
				if(parent.getCode().equals(arrayInstance.get(index, 2))) {
					privilegeType.addParents(parent);
					break;
				}
			privilegeTypes.add(privilegeType);
		}
		__logInfo__("Creating "+privilegeTypes.size()+" type de privileges");
		__inject__(PrivilegeTypeBusiness.class).saveByBatch(privilegeTypes,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("Privilege");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Privilege> privileges = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			String identifier = arrayInstance.get(index, 0);
			String code = arrayInstance.get(index, 0);
			Privilege privilege = new Privilege().setIdentifier(identifier).setCode(code).setName(arrayInstance.get(index, 1)).setType(__inject__(PrivilegeTypePersistence.class).readByBusinessIdentifier(arrayInstance.get(index, 2)));
			for(Privilege parent : privileges)
				if(parent.getCode().equals(arrayInstance.get(index, 3))) {
					privilege.addParents(parent);
					break;
				}
			privileges.add(privilege);
		}
		__logInfo__("Creating "+privileges.size()+" privileges");
		//__inject__(PrivilegeBusiness.class).saveByBatch(privileges,100);
		__inject__(PrivilegeBusiness.class).createMany(privileges);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("ministère");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Scope> sections = new ArrayList<>();
		ScopeType scopeTypeSection = __inject__(ScopeTypePersistence.class).readByBusinessIdentifier("SECTION");
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			sections.add(new Scope().setIdentifier(arrayInstance.get(index, 0)).setType(scopeTypeSection));
		__logInfo__("Creating "+sections.size()+" section");
		__inject__(ScopeBusiness.class).saveByBatch(sections,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("programme");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Scope> ugps = new ArrayList<>();
		ScopeType scopeTypeUgp = __inject__(ScopeTypePersistence.class).readByBusinessIdentifier("UGP");
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			ugps.add(new Scope().setIdentifier(arrayInstance.get(index, 0)).setType(scopeTypeUgp));
		__logInfo__("Creating "+ugps.size()+" ugp");
		__inject__(ScopeBusiness.class).saveByBatch(ugps,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("unité administrative");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Scope> uas = new ArrayList<>();
		ScopeType scopeTypeUa = __inject__(ScopeTypePersistence.class).readByBusinessIdentifier("UA");
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 100; index = index + 1)
			uas.add(new Scope().setIdentifier(arrayInstance.get(index, 0)).setType(scopeTypeUa));
		__logInfo__("Creating "+uas.size()+" ua");
		__inject__(ScopeBusiness.class).saveByBatch(uas,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("catégorie");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<FunctionType> functionTypes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			functionTypes.add(new FunctionType().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1)));
		__logInfo__("Creating "+functionTypes.size()+" type de fonction");
		__inject__(FunctionTypeBusiness.class).saveByBatch(functionTypes,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("fonction");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Function> functions = new ArrayList<>();
		Collection<FunctionScope> functionScopes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			Function function = new Function().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1))
					.setType(CollectionHelper.getElementAt(functionTypes, arrayInstance.get(index, 2).startsWith("ADMIN") ? 0 : 1));
			function.setIsProfileCreatableOnCreate(Boolean.TRUE);
			functions.add(function);
			if(arrayInstance.get(index, 3).startsWith("oui")) {
				for(Scope indexMinistry : sections)
					functionScopes.add(new FunctionScope().setFunction(function).setScope(indexMinistry));
			}
			if(arrayInstance.get(index, 4).startsWith("oui")) {
				for(Scope indexProgram : ugps)
					functionScopes.add(new FunctionScope().setFunction(function).setScope(indexProgram));
			}
			if(arrayInstance.get(index, 7).startsWith("oui")) {
				for(Scope indexAdministrativeUnit : uas)
					functionScopes.add(new FunctionScope().setFunction(function).setScope(indexAdministrativeUnit));
			}
		}
		__logInfo__("Creating "+functions.size()+" fonctions");
		__inject__(FunctionBusiness.class).saveByBatch(functions,100);
		
		__logInfo__("Creating "+functionScopes.size()+" fonctions et champs d'actions");
		__inject__(FunctionScopeBusiness.class).saveByBatch(functionScopes,100);
	}
	
}
