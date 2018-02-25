package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	public Administrator create() {
		Administrator result;

		result = new Administrator();

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = this.administratorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Administrator findOne(final int administratorId) {
		Administrator result;

		result = this.administratorRepository.findOne(administratorId);

		return result;
	}

	public Administrator save(final Administrator administrator) {
		Administrator result = administrator;
		Assert.notNull(administrator);

		result = this.administratorRepository.save(result);

		return result;
	}

	public void delete(final Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);

		this.administratorRepository.delete(administrator);
	}

	public double averageRedezvousUser() {
		return administratorRepository.averageRedezvousUser();
	}

	public double estandardDesviationRedezvousUser() {
		return administratorRepository.EstandardDesviationRedezvousUser();
	}

	public double ratioUserConRendezvous() {
		return administratorRepository.ratioUserConRendezvous();
	}

	public double ratioUserSinRendezvous() {
		return administratorRepository.ratioUserSinRendezvous();

	}

	public double averageUsersRendezvous() {
		return administratorRepository.averageUsersRendezvous();
	}

	public double estandardDesviationUsersRendezvous() {
		return administratorRepository.estandardDesviationUsersRendezvous();
	}

	public double averageRendezvousRSVPTruePerUser() {
		return administratorRepository.averageRendezvousRSVPTruePerUser();

	}

	public double estandardDesviationRendezvousRSVPTruePerUser() {
		return administratorRepository
				.estandardDesviationRendezvousRSVPTruePerUser();
	}

	public Collection<Rendezvous> topRendezvous() {
		Collection<Rendezvous> res;
		final Page<Rendezvous> pages;
		final Pageable pageable;

		pageable = new PageRequest(0, 10);
		pages = this.administratorRepository.topRendezvous(pageable);
		res = pages.getContent();

		return res;

	}

	public double averageannouncementsRendezvous() {
		return administratorRepository.averageAnnouncementsRendezvous();
	}

	public double estandardDesviationAnnouncementsUser() {
		return this.administratorRepository
				.estandardDesviationAnnouncementsUser();
	}

	public Collection<Rendezvous> redezvousSimiliars10() {
		Collection<Rendezvous> res = new ArrayList<>();
		if (!this.administratorRepository.redezvousSimiliars10().isEmpty()
				&& this.administratorRepository.redezvousSimiliars10() != null) {
			res.addAll(this.administratorRepository.redezvousSimiliars10());

		}
		return res;
	}

	public double averageNumberOfQuestionsPerRendezvous() {
		return this.administratorRepository
				.averageNumberOfQuestionsPerRendezvous();
	}

	public double estandardDesviationOfQuestionsPerRendezvous() {
		return this.administratorRepository
				.estandardDesviationOfQuestionsPerRendezvous();
	}

	public double averageOfAnswerPerQuestionsPerRendezvous() {
		return this.administratorRepository
				.averageOfAnswerPerQuestionsPerRendezvous();
	}

	public double estandardDesviationOfAnswerPerQuestionsPerRendezvous() {
		return this.administratorRepository
				.estandardDesviationOfAnswerPerQuestionsPerRendezvous();
	}

	public Collection<Rendezvous> RendezvousConMas075Announcement() {
		return this.administratorRepository.RendezvousConMas075Announcement();
	}

	public double averageRepliesComment() {
		return this.administratorRepository.averageRepliesComment();
	}

	public double estandardDesviationRepliesComment() {
		return this.administratorRepository.estandardDesviationRepliesComment();
	}

	public Administrator findByPrincipal() {
		Administrator e;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		e = this.administratorRepository.findByPrincipal(userAccount.getId());
		return e;
	}
}
