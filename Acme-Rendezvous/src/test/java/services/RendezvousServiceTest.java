
package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.GPS;
import domain.Rendezvous;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RendezvousServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private CommentService		commentService;


	// Tests --------------------

	@Test
	public void test() {

		final Rendezvous rendezvous = this.rendezvousService.create();

		final String name = "name";
		final String description = "description";
		final Date moment = new Date();
		moment.setSeconds(moment.getSeconds() + 5);
		final GPS gpsCoordinate = new GPS();
		gpsCoordinate.setLatitude(23.0);
		gpsCoordinate.setLongitude(22.0);
		gpsCoordinate.setName("name");
		final String picture = "http://www.foto.com";
		final boolean finalMode = false;
		final boolean adultOnly = true;

		rendezvous.setName(name);
		rendezvous.setDescription(description);
		rendezvous.setMoment(moment);
		rendezvous.setGpsCoordinate(gpsCoordinate);
		rendezvous.setPicture(picture);
		rendezvous.setFinalMode(finalMode);
		rendezvous.setAdultOnly(adultOnly);

		final Rendezvous saved = this.rendezvousService.save(rendezvous);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.rendezvousService.findOne(id));
		Assert.isTrue(this.rendezvousService.findAll().contains(saved));
		this.rendezvousService.delete(saved);
		Assert.isTrue(!this.rendezvousService.findAll().contains(saved));
	}
}
