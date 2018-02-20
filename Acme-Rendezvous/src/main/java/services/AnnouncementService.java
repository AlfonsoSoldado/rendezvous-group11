package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;

@Service
@Transactional
public class AnnouncementService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AnnouncementRepository announcementRepository;

	// Constructor

	public AnnouncementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Announcement create() {
		Announcement result;

		result = new Announcement();

		return result;
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> result;

		result = this.announcementRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Announcement findOne(final int announcementId) {
		Announcement result;

		result = this.announcementRepository.findOne(announcementId);

		return result;
	}

	public Announcement save(final Announcement announcement) {
		Announcement result = announcement;
		Assert.notNull(announcement);

		if (announcement.getId() == 0) {
			Date momentMade;
			momentMade = new Date(System.currentTimeMillis() - 1000);
			result.setMomentMade(momentMade);

			// TODO Añadir restricción: solo el creador del rendezVous puede
			// asociar un announcement al mismo (en controlador)
		}

		result = this.announcementRepository.save(result);

		return result;
	}

	public void delete(final Announcement announcement) {
		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId() != 0);

		this.announcementRepository.delete(announcement);

		// TODO Añadir restricción: solo el creador del rendezVous puede
		// eliminar un announcement al mismo (en controlador)
	}

}
