package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class AnnouncementService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AnnouncementRepository announcementRepository;
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private UserService userService;

	// Constructor

	public AnnouncementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Announcement create(final Rendezvous rendezvous) {
		Announcement result;

		result = new Announcement();

		result.setRendezvous(rendezvous);

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
			
			Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
			User user;
			user = userService.findByPrincipal();
			rendezvouses.addAll(user.getRendezvous());
			Assert.isTrue(rendezvouses.contains(announcement.getRendezvous()));

			// TODO Añadir restricción: solo el creador del rendezVous puede
			// asociar un announcement al mismo (en controlador)
		}

		result = this.announcementRepository.save(result);

		return result;
	}

	public void delete(final Announcement announcement) {
		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId() != 0);

		Rendezvous rendezvous;
		rendezvous = announcement.getRendezvous();
		rendezvous.getAnnouncement().remove(announcement);

		this.announcementRepository.delete(announcement);
	}

	public void deleteAll(Collection<Announcement> announcements) {
		Assert.notNull(announcements);

		this.announcementRepository.delete(announcements);
	}

	public Collection<Announcement> findAnnouncementsByRendezvous(int id) {
		Collection<Announcement> res = new ArrayList<Announcement>();
		res.addAll(announcementRepository.findAnnouncementsByRendezvous(id));
		res = announcementRepository.findAnnouncementsByRendezvous(id);

		Assert.notNull(res);
		return res;

	}

}
