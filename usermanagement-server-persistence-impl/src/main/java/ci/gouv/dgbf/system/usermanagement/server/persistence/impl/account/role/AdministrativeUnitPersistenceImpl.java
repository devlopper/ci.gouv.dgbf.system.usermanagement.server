package ci.gouv.dgbf.system.usermanagement.server.persistence.impl.account.role;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.utility.field.FieldValueCopy;
import org.cyk.utility.request.RequestProcessor;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

import ci.gouv.dgbf.system.usermanagement.server.persistence.api.account.role.AdministrativeUnitPersistence;
import ci.gouv.dgbf.system.usermanagement.server.persistence.entities.account.role.AdministrativeUnit;

@Singleton
public class AdministrativeUnitPersistenceImpl extends AbstractPersistenceEntityImpl<AdministrativeUnit> implements AdministrativeUnitPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __processAfterRead__(AdministrativeUnit administrativeUnit) {
		super.__processAfterRead__(administrativeUnit);
		/*AdministrativeUnit remote = (AdministrativeUnit) __inject__(RequestProcessor.class)
				.setUniformResourceIdentifierString("http://10.3.4.20:32202/sib/classification-par-programme/api/v1/programmes/code/"+administrativeUnit.getIdentifier())
				.setResponseEntityClass(AdministrativeUnit.class)
				.execute().getOutput();
		__inject__(FieldValueCopy.class).setSource(remote).setDestination(administrativeUnit).setIsOverridable(Boolean.FALSE).execute();
		*/
		//administrativeUnit.setName(remote.getName());
	}
	
}
