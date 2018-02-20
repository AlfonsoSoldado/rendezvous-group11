
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Question;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UserRepository	userRepository;


	// Constructor

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create() {
		User result;

		result = new User();
		result.setComment(new ArrayList<Comment>());
		result.setRendezvous(new ArrayList<Rendezvous>());
		result.setRsvp(new ArrayList<RSVP>());
		result.setQuestion(new ArrayList<Question>());

		return result;
	}

	public Collection<User> findAll() {
		Collection<User> result;

		result = this.userRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public User findOne(final int userId) {
		User result;

		result = this.userRepository.findOne(userId);

		return result;
	}

	public User save(final User user) {
		User result = user;
		Assert.notNull(user);

		result = this.userRepository.save(result);

		return result;
	}

	public void delete(final User user) {
		Assert.notNull(user);
		Assert.isTrue(user.getId() != 0);

		this.userRepository.delete(user);
	}

	public User findByPrincipal() {
		User e;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		e = this.userRepository.findByPrincipal(userAccount.getId());
		return e;
	}

	public User findCreator(final int rendezvousId) {
		User result;
		result = this.userRepository.findCreator(rendezvousId);
		Assert.notNull(result);

		return result;
	}

	public Collection<User> findAttendants(final int rendezvousId) {
		Collection<User> result;
		result = this.userRepository.findAttendants(rendezvousId);
		Assert.notNull(result);

		return result;
	}

	public Collection<User> findRUserSVPbyRendezvous(final int rendezvousId) {
		Collection<User> result;
		result = this.userRepository.findUserRSVPbyRendezvous(rendezvousId);
		Assert.notNull(result);

		return result;
	}

}
