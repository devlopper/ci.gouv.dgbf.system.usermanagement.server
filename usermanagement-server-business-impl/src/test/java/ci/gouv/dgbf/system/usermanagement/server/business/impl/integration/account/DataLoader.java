package ci.gouv.dgbf.system.usermanagement.server.business.impl.integration.account;

import java.util.ArrayList;
import java.util.Collection;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.array.ArrayInstanceTwoDimensionString;
import org.cyk.utility.file.excel.FileExcelSheetDataArrayReader;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.AdministrativeUnitBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.MinistryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProgramBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleCategoryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RoleFunctionBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.RolePosteBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.impl.ApplicationScopeLifeCycleListener;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleCategory;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RoleFunction;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.RolePoste;

public class DataLoader extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBeforeCallCountIsZero__() throws Exception {
		super.__listenBeforeCallCountIsZero__();
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Test
	public void load() throws Exception{
		FileExcelSheetDataArrayReader reader;
		ArrayInstanceTwoDimensionString arrayInstance;
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("ministère");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Ministry> ministries = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount(); index = index + 1)
			ministries.add(new Ministry().setIdentifier(arrayInstance.get(index, 0)));
		__logInfo__("Creating "+ministries.size()+" ministries");
		__inject__(MinistryBusiness.class).createMany(ministries);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("programme");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<Program> programs = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			programs.add(new Program().setIdentifier(arrayInstance.get(index, 0)));
		__logInfo__("Creating "+programs.size()+" programs");
		__inject__(ProgramBusiness.class).createMany(programs);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("unité administrative");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<AdministrativeUnit> administrativeUnits = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			administrativeUnits.add(new AdministrativeUnit().setIdentifier(arrayInstance.get(index, 0)));
		__logInfo__("Creating "+administrativeUnits.size()+" programs");
		__inject__(AdministrativeUnitBusiness.class).createMany(administrativeUnits);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("catégorie");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<RoleCategory> roleCategories = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			roleCategories.add(new RoleCategory().setIdentifier(arrayInstance.get(index, 0)));
		__logInfo__("Creating role categories");
		__inject__(RoleCategoryBusiness.class).createMany(roleCategories);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("fonction");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		arrayInstance = reader.execute().getOutput();
		Collection<RoleFunction> roleFonctions = new ArrayList<>();
		Collection<RolePoste> rolePostes = new ArrayList<>();
		for(Integer index  = 0; index < arrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1) {
			RoleFunction function = new RoleFunction().setIdentifier(arrayInstance.get(index, 0));
			roleFonctions.add(function);
			if(arrayInstance.get(index, 3).startsWith("oui")) {
				for(Ministry indexMinistry : ministries)
					rolePostes.add(new RolePoste().setFunction(function).setMinistry(indexMinistry));
			}
			if(arrayInstance.get(index, 4).startsWith("oui")) {
				for(Program indexProgram : programs)
					rolePostes.add(new RolePoste().setFunction(function).setProgram(indexProgram));
			}
			if(arrayInstance.get(index, 7).startsWith("oui")) {
				for(AdministrativeUnit indexAdministrativeUnit : administrativeUnits)
					rolePostes.add(new RolePoste().setFunction(function).setAdministrativeUnit(indexAdministrativeUnit));
			}
		}
		__logInfo__("Creating "+roleFonctions.size()+" role functions");
		__inject__(RoleFunctionBusiness.class).createMany(roleFonctions);
		
		__logInfo__("Creating "+rolePostes.size()+" role postes");
		__inject__(RolePosteBusiness.class).createMany(rolePostes);
	}
	
	
}
