
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	private Date	dateBorn;


	@Temporal(TemporalType.DATE)
	@Past
	public Date getDateBorn() {
		return this.dateBorn;
	}

	public void setDateBorn(final Date dateBorn) {
		this.dateBorn = dateBorn;
	}
	
	// Relationships --------------------------------------------------

	private Comment comment;
	private Rendezvous rendezvous;
	private RSVP rsvp;
	
	@OneToMany(mappedBy = "user")
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@ManyToMany
	public Rendezvous getRendezvous() {
		return rendezvous;
	}

	public void setRendezvous(Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}

	@OneToMany
	public RSVP getRsvp() {
		return rsvp;
	}

	public void setRsvp(RSVP rsvp) {
		this.rsvp = rsvp;
	}
	
}
