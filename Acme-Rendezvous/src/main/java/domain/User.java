
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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

	private Collection<Comment> comment;
	private Collection<Rendezvous> rendezvous;
	private Collection<RSVP> rsvp;
	private Collection<Question> question;
	
	@OneToMany(mappedBy = "user")
	public Collection<Comment> getComment() {
		return comment;
	}

	public void setComment(Collection<Comment> comment) {
		this.comment = comment;
	}

	@OneToMany
	public Collection<Rendezvous> getRendezvous() {
		return rendezvous;
	}

	public void setRendezvous(Collection<Rendezvous> rendezvous) {
		this.rendezvous = rendezvous;
	}

	@OneToMany
	public Collection<RSVP> getRsvp() {
		return rsvp;
	}

	public void setRsvp(Collection<RSVP> rsvp) {
		this.rsvp = rsvp;
	}

	@OneToMany
	public Collection<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Collection<Question> question) {
		this.question = question;
	}
	
}
