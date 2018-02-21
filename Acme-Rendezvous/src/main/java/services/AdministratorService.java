package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Rendezvous;
import repositories.AdministratorRepository;

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

	public double EstandardDesviationRedezvousUser() {
		return administratorRepository.EstandardDesviationRedezvousUser();
	}

	public double ratioCreateAndNoCreateRendezvousUser() {
		return administratorRepository.ratioCreateAndNoCreateRendezvousUser();
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

	public double EstandardDesviationUsersRendezvous() {
		return administratorRepository.estandardDesviationUsersRendezvous();
	}

	public double averageRendezvousRSVPTruePerUser() {
		return administratorRepository.averageRendezvousRSVPTruePerUser();

	}

	public double EstandardDesviationRendezvousRSVPTruePerUser() {
		return administratorRepository.estandardDesviationRendezvousRSVPTruePerUser();
	}

	public Collection<Rendezvous> topRendezvous() {
		return administratorRepository.topRendezvous(createPageableRequest());

	}

	private Pageable createPageableRequest() {
		return new PageRequest(0, 10);
	}

	public double averageannouncementsRendezvous() {
		return administratorRepository.averageAnnouncementsRendezvous();
	}

	/*
	 * The average and the standard deviation of announcements per rendezvous. The
	 * rendezvouses that whose number of announcements is above 75% the average
	 * number of announcements per rendezvous. The rendezvouses that are linked to a
	 * number of rendezvouses that is great-er than the average plus 10%. The
	 * average and the standard deviation of the number of questions per
	 * ren-dezvous. The average and the standard deviation of the number of answers
	 * to the questions per rendezvous. The average and the standard deviation of
	 * replies per comment
	 */

}
