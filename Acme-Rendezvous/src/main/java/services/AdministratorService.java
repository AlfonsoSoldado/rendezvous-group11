package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrator;
import repositories.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	
	

	public Administrator create() {
		Administrator result;

		result = new  Administrator();

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
	
}
