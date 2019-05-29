package ci.gouv.dgbf.system.usermanagement.server.business.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.array.ArrayInstanceTwoDimensionString;
import org.cyk.utility.file.excel.FileExcelSheetDataArrayReader;
import org.cyk.utility.network.protocol.ProtocolDefaults;
import org.cyk.utility.security.Credentials;
import org.cyk.utility.system.node.SystemNodeServer;

import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.AdministrativeUnitBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.MinistryBusiness;
import ci.gouv.dgbf.system.usermanagement.server.business.api.account.role.ProgramBusiness;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Ministry;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.Program;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		__inject__(SystemNodeServer.class).setName("Gestion des utilisateurs");
		
		__inject__(ProtocolDefaults.class).getSimpleMailTransfer().setHost("smtp.gmail.com").setPort(587).setIsAuthenticationRequired(Boolean.TRUE)
		.setIsSecuredConnectionRequired(Boolean.TRUE).setAuthenticationCredentials(__inject__(Credentials.class).setIdentifier("dgbfdtideveloppers").setSecret("dgbf2016dti"));
		
		__inject__(ci.gouv.dgbf.system.usermanagement.server.persistence.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
		
		__initializePersistenceData__();
	}
	
	@Override
	public void __destroy__(Object object) {}
	
	/**/
	
	private void __initializePersistenceData__() {
		FileExcelSheetDataArrayReader reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("ministère");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString ministryArrayInstance = reader.execute().getOutput();
		Collection<Ministry> ministries = new ArrayList<>();
		for(Integer index  = 0; index < ministryArrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			ministries.add(new Ministry().setIdentifier(ministryArrayInstance.get(index, 0)));
		__logInfo__("Creating ministry");
		__inject__(MinistryBusiness.class).createMany(ministries);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("programme");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString programArrayInstance = reader.execute().getOutput();
		Collection<Program> programs = new ArrayList<>();
		for(Integer index  = 0; index < programArrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			programs.add(new Program().setIdentifier(programArrayInstance.get(index, 0)));
		__logInfo__("Creating program");
		__inject__(ProgramBusiness.class).createMany(programs);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(getClass().getResourceAsStream("data.xlsx")).setSheetName("unité administrative");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString administrativeUnitArrayInstance = reader.execute().getOutput();
		Collection<AdministrativeUnit> administrativeUnits = new ArrayList<>();
		for(Integer index  = 0; index < administrativeUnitArrayInstance.getFirstDimensionElementCount() && index < 30; index = index + 1)
			administrativeUnits.add(new AdministrativeUnit().setIdentifier(administrativeUnitArrayInstance.get(index, 0)));
		__logInfo__("Creating administrative unit");
		__inject__(AdministrativeUnitBusiness.class).createMany(administrativeUnits);
	}
}
