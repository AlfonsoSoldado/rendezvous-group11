
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
		this.rendezvousRepository.save(rendezvous);
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


		
}
