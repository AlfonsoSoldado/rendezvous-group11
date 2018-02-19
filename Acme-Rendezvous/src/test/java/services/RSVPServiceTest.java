
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.RSVP;
import domain.Rendezvous;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RSVPServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private RSVPService			rsvpService;

	@Autowired
	private RendezvousService	rendezvouService;


	// Tests --------------------

	@Test
	public void test() {

		final RSVP rsvp = this.rsvpService.create();

		final boolean confirmed = true;
		rsvp.setConfirmed(confirmed);
		final Rendezvous rendezvous = this.rendezvouService.findOne(27);
		rsvp.setRendezvous(rendezvous);

		final RSVP saved = this.rsvpService.save(rsvp);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.rsvpService.findOne(id));
		saved.setConfirmed(false);
		Assert.isTrue(this.rsvpService.findOne(saved.getId()).getConfirmed() == false);
		Assert.isTrue(this.rsvpService.findAll().contains(saved));
		this.rsvpService.delete(saved);
		Assert.isTrue(!this.rsvpService.findAll().contains(saved));
	}
}
