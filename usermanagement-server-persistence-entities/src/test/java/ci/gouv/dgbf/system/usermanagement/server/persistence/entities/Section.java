package ci.gouv.dgbf.system.usermanagement.server.persistence.entities;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Section {

	@JsonbProperty(value="code")
	private String identifier;
	
	@JsonbProperty(value="libelle")
	private String name;
	
	@JsonbProperty(value="uuid")
	private String link;
	
}
