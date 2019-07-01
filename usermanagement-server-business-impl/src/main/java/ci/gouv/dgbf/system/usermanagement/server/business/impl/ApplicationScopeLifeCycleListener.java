package ci.gouv.dgbf.system.usermanagement.server.business.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.array.ArrayInstanceTwoDimensionString;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.file.excel.FileExcelSheetDataArrayReader;
import org.cyk.utility.server.business.AbstractApplicationScopeLifeCycleListenerImplementation;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ScopeTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.FunctionScopeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Scope;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.ScopeType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Function;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.FunctionScope;

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
		//super.__saveData__();
		ScopeType section = new ScopeType().setCode("SECTION").setName("Section");
		ScopeType ugp = new ScopeType().setCode("UGP").setName("Unité de gestion programmatique");
		ScopeType ua = new ScopeType().setCode("UA").setName("Unité administrative");
		
		__inject__(ScopeTypeBusiness.class).createMany(__inject__(CollectionHelper.class).instanciate(section,ugp,ua));
		
		FileExcelSheetDataArrayReader reader;
		ArrayInstanceTwoDimensionString arrayInstance;
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("ministère");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Scope> sections = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			sections.add(new Scope().setIdentifier(arrayInstance.get(index, 0)).setType(section));
		__logInfo__("Creating "+sections.size()+" section");
		__inject__(ScopeBusiness.class).saveManyByBatch(sections,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("programme");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Scope> ugps = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 1000; index = index + 1)
			ugps.add(new Scope().setIdentifier(arrayInstance.get(index, 0)).setType(ugp));
		__logInfo__("Creating "+ugps.size()+" ugp");
		__inject__(ScopeBusiness.class).saveManyByBatch(ugps,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("unité administrative");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Scope> uas = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 1000; index = index + 1)
			uas.add(new Scope().setIdentifier(arrayInstance.get(index, 0)).setType(ua));
		__logInfo__("Creating "+uas.size()+" ua");
		__inject__(ScopeBusiness.class).saveManyByBatch(uas,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("catégorie");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<FunctionCategory> roleCategories = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 1000; index = index + 1)
			roleCategories.add(new FunctionCategory().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1)));
		__logInfo__("Creating "+roleCategories.size()+" categories de fonction");
		__inject__(FunctionCategoryBusiness.class).saveManyByBatch(roleCategories,100);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("fonction");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Function> functions = new ArrayList<>();
		Collection<FunctionScope> functionScopes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 1000; index = index + 1) {
			Function function = new Function().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1))
					.setCategory(__inject__(CollectionHelper.class).getElementAt(roleCategories, arrayInstance.get(index, 2).startsWith("ADMIN") ? 0 : 1));
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
		__inject__(FunctionBusiness.class).saveManyByBatch(functions,100);
		
		__logInfo__("Creating "+functionScopes.size()+" fonctions et champs d'actions");
		__inject__(FunctionScopeBusiness.class).saveManyByBatch(functionScopes,100);
	}
	
}
