
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
import domain.Announcement;
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
	private AnnouncementService	announcementService;


	// Tests --------------------

	@SuppressWarnings("deprecation")
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
		gpsCoordinate.setNamePlace("name");
		final String picture = "http://www.foto.com";
		final boolean finalMode = false;
		final boolean adultOnly = true;

		final Announcement announcement = this.announcementService.create(rendezvous);
		final String description2 = "Descripción 1";
		final String title = "Título 1";
		announcement.setTitle(title);
		announcement.setDescription(description2);
		final Announcement saved2 = this.announcementService.save(announcement);

		rendezvous.setName(name);
		rendezvous.setDescription(description);
		rendezvous.setMoment(moment);
		rendezvous.setGpsCoordinate(gpsCoordinate);
		rendezvous.setPicture(picture);
		rendezvous.setFinalMode(finalMode);
		rendezvous.setAdultOnly(adultOnly);
		rendezvous.getAnnouncement().add(saved2);

		final Rendezvous saved = this.rendezvousService.save(rendezvous);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.rendezvousService.findOne(id));
		saved.setAdultOnly(false);
		Assert.isTrue(this.rendezvousService.findOne(saved.getId()).getAdultOnly() == false);
		Assert.isTrue(this.rendezvousService.findAll().contains(saved));
		this.rendezvousService.delete(saved);
		Assert.isTrue(!this.rendezvousService.findAll().contains(saved));
	}
}
