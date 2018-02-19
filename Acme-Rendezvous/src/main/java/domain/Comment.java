
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	text;
	private Date	momentMade;
	private String	picture;


	@NotBlank
	public String getText() {
		return this.text;
	}
	public void setText(final String text) {
		this.text = text;
	}

	@Past
	public Date getMomentMade() {
		return this.momentMade;
	}
	public void setMomentMade(final Date momentMade) {
		this.momentMade = momentMade;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}

	//Relationships -----------------------------------------------
	
	private Collection<Comment> replies;

	@OneToMany
	public Collection<Comment> getReplies() {
		return replies;
	}
	public void setReplies(Collection<Comment> replies) {
		this.replies = replies;
	}

}
