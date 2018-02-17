
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

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

	@Future
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

	private Comment comment;
	private User attender;
	private User creator;
	private Announcement announcement;
	private Rendezvous similar;
	
	@OneToMany
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@ManyToMany
	public User getAttender() {
		return attender;
	}
	public void setAttender(User attender) {
		this.attender = attender;
	}

	@ManyToOne(optional = true)
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}

	@OneToMany
	public Announcement getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@OneToOne(optional = true)
	public Rendezvous getSimilar() {
		return similar;
	}
	public void setSimilar(Rendezvous similar) {
		this.similar = similar;
	}
}
