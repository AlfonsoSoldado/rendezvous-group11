
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RSVPRepository;
import domain.RSVP;

@Service
@Transactional
public class RSVPService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RSVPRepository	rsvpRepository;


	// Constructor

	public RSVPService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public RSVP create() {
		RSVP result;

		result = new RSVP();

		return result;
	}

	public Collection<RSVP> findAll() {
		Collection<RSVP> result;

		result = this.rsvpRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public RSVP findOne(final int rsvpId) {
		RSVP result;

		result = this.rsvpRepository.findOne(rsvpId);

		return result;
	}

	public RSVP save(final RSVP rsvp) {
		RSVP result = rsvp;
		Assert.notNull(rsvp);

		Assert.isTrue(rsvp.getConfirmed() == true);

		result = this.rsvpRepository.save(result);

		//TODO Añadir restricción: number of answers must be the same as the number of questions of the rendezvous

		return result;
	}

	public void delete(final RSVP rsvp) {
		Assert.notNull(rsvp);
		Assert.isTrue(rsvp.getId() != 0);

		this.rsvpRepository.delete(rsvp);

		//TODO Añadir restricción: solo el creador del RSVP puede eliminarlo
	}

}
