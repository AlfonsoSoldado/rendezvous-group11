
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	@Autowired
	private ActorService	actorService;


	@Test
	public void test() {
		this.authenticate("admin");
		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor.getUserAccount().getUsername().equals("admin"));
		Assert.isTrue(this.actorService.findAll().contains(actor));
		Assert.notNull(this.actorService.findOne(actor.getId()));
		Assert.isTrue(this.actorService.isAuthenticated() == true);
	}
}
