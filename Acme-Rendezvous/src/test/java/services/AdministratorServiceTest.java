package services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
public class AdministratorServiceTest extends AbstractTest {
	@Autowired
	private AdministratorService administratorService;

	@Test
	public void test() {

		final Administrator administrator = this.administratorService.create();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		final List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);

		final UserAccount administratorAccount = new UserAccount();
		administratorAccount.setAuthorities(authorities);
		administratorAccount.setUsername("xxxxx");
		administratorAccount.setPassword("xxxxx");

		administrator.setUserAccount(administratorAccount);
		administrator.setName("name");
		administrator.setSurname("surname");
		administrator.setEmail("email@email.com");
		administrator.setPhoneNumber("phone");
		administrator.setPostalAddress(41020);

		Administrator saved = this.administratorService.save(administrator);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.administratorService.findOne(id));
		saved.setName("updated");
		saved = this.administratorService.save(saved);
		Assert.isTrue(this.administratorService.findOne(saved.getId()).getName() == "updated");
		Assert.isTrue(this.administratorService.findAll().contains(saved));
		this.administratorService.delete(saved);
		Assert.isTrue(!this.administratorService.findAll().contains(saved));
	}

}
