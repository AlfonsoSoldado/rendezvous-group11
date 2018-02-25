
package services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Announcement;
import domain.Comment;
import domain.Rendezvous;
import domain.User;
import repositories.RendezvousRepository;
import security.Authority;

@Service
@Transactional
public class RendezvousService {

	// Managed repository

	@Autowired
	private RendezvousRepository rendezvousRepository;

	// Suporting services

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService administratorService;
	// Constructor
	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService service;

	public RendezvousService() {
		super();
	}

	// Simple CRUD methods

	public Rendezvous create() {
		final Rendezvous res = new Rendezvous();
		res.setComment(new ArrayList<Comment>());
		res.setAttendant(new ArrayList<User>());
		res.setSimilar(new ArrayList<Rendezvous>());
		res.setAnnouncement(new ArrayList<Announcement>());
		res.setDeleted(false);

		return res;
	}

	public Collection<Rendezvous> findAll() {
		Collection<Rendezvous> res;
		res = this.rendezvousRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Rendezvous findOne(final int rendezvousId) {
		Assert.isTrue(rendezvousId != 0);
		Rendezvous res;
		res = this.rendezvousRepository.findOne(rendezvousId);
		Assert.notNull(res);
		return res;
	}

	public Rendezvous save(final Rendezvous rendezvous) {
		Assert.notNull(rendezvous);

		if (rendezvous.getId() != 0) {
			User principal;
			principal = this.userService.findByPrincipal();

			User creator;
			creator = this.userService.findCreator(rendezvous.getId());

			Assert.isTrue(principal.equals(creator));
		}

		Rendezvous res;
		res = this.rendezvousRepository.save(rendezvous);
		return res;

	}

	public void delete(final Rendezvous rendezvous) {
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);
		Assert.isTrue(this.rendezvousRepository.exists(rendezvous.getId()));

		rendezvous.setFinalMode(true);
		rendezvous.setDeleted(true);
		this.save(rendezvous);
	}

	public Rendezvous findRendezvousByComment(final int commentId) {
		Rendezvous res;
		res = this.rendezvousRepository.findRendezvousByComment(commentId);

		return res;
	}

	public Collection<Rendezvous> findByAttendantId(final int attendantId) {
		Collection<Rendezvous> res;
		res = this.rendezvousRepository.findByAttendantId(attendantId);

		return res;
	}

	public void DeleteAdmin(Rendezvous rendezvous) {
		System.out.println(Boolean.valueOf(rendezvous != null).toString() + Boolean.valueOf(rendezvous.getId() != 0));
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);
		for (Authority au : administratorService.findByPrincipal().getUserAccount().getAuthorities()) {
			System.out.println(au);
			System.out.println(au);
			System.out.println(au.getAuthority().equals("ADMIN"));
			Assert.isTrue(au.getAuthority().equals("ADMIN"));
		}

		// quito este rendezvous de la lista de similares de los otros rendezvous
		List<Rendezvous> rendezvouses = new ArrayList<>();
		Collection<Rendezvous> comprobacion = this.rendezvousRepository
				.findRendezvousContainThisAsSimilar(rendezvous.getId());
		if (comprobacion != null && comprobacion.isEmpty()) {
			rendezvouses.addAll(this.rendezvousRepository.findRendezvousContainThisAsSimilar(rendezvous.getId()));
			for (Rendezvous r : rendezvouses) {
				List<Rendezvous> actualiza = new ArrayList<>(r.getSimilar());
				actualiza.remove(rendezvous);
				r.setSimilar(actualiza);
				this.rendezvousRepository.save(r);
			}
		}
		// borro los annoucements asociados
		this.announcementService.deleteAll(rendezvous.getAnnouncement());
		// borro los comment asociados
		for (Comment comment : rendezvous.getComment()) {
			if (comment!=null) {
				this.commentService.delete(comment);

			}
		}

		User user =this.userService.findCreator(rendezvous.getId());
		List<Rendezvous> rendezvousUser= new ArrayList<>();
		rendezvousUser.addAll(user.getRendezvous());
		System.out.println(rendezvousUser);
		rendezvousUser.remove(rendezvous);
		System.out.println(rendezvousUser);
		user.setRendezvous(rendezvousUser);
		this.userService.save(user);
		this.rendezvousRepository.delete(rendezvous.getId());

	}

		
}
