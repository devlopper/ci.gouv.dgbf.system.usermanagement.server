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

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.PosteLocationTypeBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocation;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.PosteLocationType;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

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
		PosteLocationType ministere = new PosteLocationType().setCode("MINISTERE").setName("Ministère");
		PosteLocationType programme = new PosteLocationType().setCode("PROGRAMME").setName("Programme");
		PosteLocationType ua = new PosteLocationType().setCode("UNITE_ADMINISTRATIVE").setName("Unité administrative");
		PosteLocationType institution = new PosteLocationType().setCode("INSTITUTION").setName("Institution");
		
		__inject__(PosteLocationTypeBusiness.class).createMany(__inject__(CollectionHelper.class).instanciate(ministere,programme,ua,institution));
		
		FileExcelSheetDataArrayReader reader;
		ArrayInstanceTwoDimensionString arrayInstance;
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("ministère");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<PosteLocation> ministries = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			ministries.add(new PosteLocation().setIdentifier(arrayInstance.get(index, 0)).setType(ministere));
		__logInfo__("Creating "+ministries.size()+" ministries");
		__inject__(PosteLocationBusiness.class).createMany(ministries);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("programme");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<PosteLocation> programs = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			programs.add(new PosteLocation().setIdentifier(arrayInstance.get(index, 0)).setType(programme));
		__logInfo__("Creating "+programs.size()+" programs");
		__inject__(PosteLocationBusiness.class).createMany(programs);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("unité administrative");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<PosteLocation> administrativeUnits = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			administrativeUnits.add(new PosteLocation().setIdentifier(arrayInstance.get(index, 0)).setType(ua));
		__logInfo__("Creating "+administrativeUnits.size()+" administratives units");
		__inject__(PosteLocationBusiness.class).createMany(administrativeUnits);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("catégorie");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<RoleCategory> roleCategories = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			roleCategories.add(new RoleCategory().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1)));
		__logInfo__("Creating "+roleCategories.size()+" role categories");
		__inject__(RoleCategoryBusiness.class).createMany(roleCategories);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("fonction");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<RoleFunction> roleFonctions = new ArrayList<>();
		Collection<RolePoste> rolePostes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1) {
			RoleFunction function = new RoleFunction().setCode(arrayInstance.get(index, 0)).setName(arrayInstance.get(index, 1))
					.setCategory(__inject__(CollectionHelper.class).getElementAt(roleCategories, arrayInstance.get(index, 2).startsWith("ADMIN") ? 0 : 1));
			roleFonctions.add(function);
			if(arrayInstance.get(index, 3).startsWith("oui")) {
				for(PosteLocation indexMinistry : ministries)
					rolePostes.add(new RolePoste().setFunction(function).setLocation(indexMinistry));
			}
			if(arrayInstance.get(index, 4).startsWith("oui")) {
				for(PosteLocation indexProgram : programs)
					rolePostes.add(new RolePoste().setFunction(function).setLocation(indexProgram));
			}
			if(arrayInstance.get(index, 7).startsWith("oui")) {
				for(PosteLocation indexAdministrativeUnit : administrativeUnits)
					rolePostes.add(new RolePoste().setFunction(function).setLocation(indexAdministrativeUnit));
			}
		}
		__logInfo__("Creating "+roleFonctions.size()+" role functions");
		__inject__(RoleFunctionBusiness.class).createMany(roleFonctions);
		
		__logInfo__("Creating "+rolePostes.size()+" role postes");
		__inject__(RolePosteBusiness.class).createMany(rolePostes);
	}
	
}
