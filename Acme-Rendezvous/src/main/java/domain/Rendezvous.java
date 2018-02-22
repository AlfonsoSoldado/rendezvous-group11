
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Rendezvous extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private String	description;
	private Date	moment;
	private GPS		gpsCoordinate;
	private String	picture;
	private boolean	finalMode;
	private boolean	adultOnly;


	@NotBlank
	@Column(insertable = false, updatable = false)
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public GPS getGpsCoordinate() {
		return this.gpsCoordinate;
	}
	public void setGpsCoordinate(final GPS gpsCoordinate) {
		this.gpsCoordinate = gpsCoordinate;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public boolean getFinalMode() {
		return this.finalMode;
	}
	public void setFinalMode(final boolean finalMode) {
		this.finalMode = finalMode;
	}

	public boolean getAdultOnly() {
		return this.adultOnly;
	}
	public void setAdultOnly(final boolean adultOnly) {
		this.adultOnly = adultOnly;
	}


	//Relationships -------------------------------------------------

	private Collection<Comment>			comment;
	private Collection<User>			attendant;
	private Collection<Announcement>	announcement;
	private Collection<Rendezvous>		similar;


	@OneToMany
	public Collection<Comment> getComment() {
		return this.comment;
	}
	public void setComment(final Collection<Comment> comment) {
		this.comment = comment;
	}

	@OneToMany
	public Collection<User> getAttendant() {
		return this.attendant;
	}
	public void setAttendant(final Collection<User> attendant) {
		this.attendant = attendant;
	}

	@OneToMany
	public Collection<Announcement> getAnnouncement() {
		return this.announcement;
	}
	public void setAnnouncement(final Collection<Announcement> announcement) {
		this.announcement = announcement;
	}

	@OneToMany
	public Collection<Rendezvous> getSimilar() {
		return this.similar;
	}
	public void setSimilar(final Collection<Rendezvous> similar) {
		this.similar = similar;
	}

}
