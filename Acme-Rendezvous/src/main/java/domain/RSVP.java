
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class RSVP extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private boolean	confirmed;

	public boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(final boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	// Relationships ----------------------------------------------------------

	private Rendezvous rendezvous;
	
	@ManyToOne(optional = false)
	public Rendezvous getRendezvous() {
		return rendezvous;
	}

	public void setRendezvous(Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}

}
