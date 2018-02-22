
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

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class UserServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private UserService	userService;


	// Tests --------------------

	@Test
	public void test() {
		this.authenticate("user1");

		final User user = this.userService.create();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.USER);
		final List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);

		final UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(authorities);
		userAccount.setUsername("xxxxx");
		userAccount.setPassword("xxxxx");

		user.setUserAccount(userAccount);
		user.setName("name");
		user.setSurname("surname");
		user.setEmail("email@email.com");
		user.setPhoneNumber("phone");
		user.setPostalAddress(41020);

		User saved = this.userService.save(user);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.userService.findOne(id));
		saved.setName("updated");
		saved = this.userService.save(saved);
		Assert.isTrue(this.userService.findOne(saved.getId()).getName() == "updated");
		Assert.isTrue(this.userService.findAll().contains(saved));
		this.userService.delete(saved);
		Assert.isTrue(!this.userService.findAll().contains(saved));

		Assert.isTrue(this.userService.findByPrincipal().getUserAccount().getUsername().equals("user1"));
		Assert.isTrue(this.userService.findCreator(27).getId() == 25);
		//TODO
		//Assert.isTrue(this.userService.findAttendants(28).contains(this.userService.findOne(26)));

	}
}
