package ci.gouv.dgbf.system.usermanagement.server.representation.entities.account;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.cyk.utility.__kernel__.object.__static__.representation.LocalDateTimeAdapter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor @ToString
public class UserAccountInterimDto extends AbstractUserAccountInterimDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	
	public UserAccountDto getUserAccount(Boolean injectIfNull) {
		return super.getUserAccount(injectIfNull);
	}
	
	public UserAccountDto getInterim(Boolean injectIfNull) {
		return super.getInterim(injectIfNull);
	}
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getToDate() {
		return toDate;
	}
	
	/**/
	
}
