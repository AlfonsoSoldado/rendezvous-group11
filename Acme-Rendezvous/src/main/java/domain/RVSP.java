
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class RVSP extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private boolean	confirmed;


	public boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(final boolean confirmed) {
		this.confirmed = confirmed;
	}

}
