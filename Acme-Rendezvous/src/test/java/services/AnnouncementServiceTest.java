
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Announcement;
import domain.Rendezvous;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AnnouncementServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private AnnouncementService	announcementService;
	
	@Autowired
	private RendezvousService	rendezvousService;


	// Tests --------------------

	@Test
	public void test() {

		final Rendezvous rendezvous = this.rendezvousService.create();
		
		final Announcement announcement = this.announcementService.create(rendezvous);

		final String description = "Descripción 1";
		final String title = "Título 1";
		announcement.setTitle(title);
		announcement.setDescription(description);

		Announcement saved = this.announcementService.save(announcement);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.announcementService.findOne(id));
		saved.setTitle("updated");
		saved = this.announcementService.save(saved);
		Assert.isTrue(this.announcementService.findOne(saved.getId()).getTitle() == "updated");
		Assert.isTrue(this.announcementService.findAll().contains(saved));
		this.announcementService.delete(saved);
		Assert.isTrue(!this.announcementService.findAll().contains(saved));
	}
}
