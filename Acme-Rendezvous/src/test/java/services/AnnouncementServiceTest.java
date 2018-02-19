
package services;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Note;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private NoteService	noteService;

	@Autowired
	private TripService	tripService;


	// Tests --------------------

	@Test
	public void testSave() {
		this.authenticate("auditor1");

		//Actualizo este curriculum
		final Note n = this.noteService.findOne((this.getEntityId("note1")));

		//Actualizo cambiando el remark
		n.setRemark("yyy");
		final Note saved = this.noteService.save(n);

		final Collection<Note> notes = this.noteService.findAll();
		Assert.isTrue(notes.contains(saved), "----- Fallo metodo save -----");

	}

	@Test
	public void testFindAll() {

		final Collection<Note> notes = this.noteService.findAll();
		Assert.isTrue(notes.size() > 0, "----- Fallo metodo findAll -----");

	}

	@Test
	public void testCreate() {
		final Trip t = this.tripService.findOne((this.getEntityId("trip1")));

		Note note;
		note = this.noteService.create();
		note.setRemark("xxx");
		note.setReply("yyy");
		note.setReplyMoment(new Date());
		note.setTrip(t);
		this.noteService.save(note);

	}

	@Test
	public void testFindOne() {
		final Note note = this.noteService.findOne((this.getEntityId("note1")));
		Assert.isTrue(!note.equals(null), "----- Fallo metodo findOne -----");

	}

	@Test
	public void testDelete() {
		final Note n = this.noteService.findOne((this.getEntityId("note1")));
		this.noteService.delete(n);
	}

	@Test
	public void testDeleteByOwner() {
		final Note n = this.noteService.findOne((this.getEntityId("note1")));
		this.noteService.deleteByOwner(n);

		this.noteService.findAll();
	}
	@Test
	public void testFindAllNotesByAuditor() {
		final Collection<Note> result = this.noteService.findAllNotesByAuditor(2817);
		Assert.notNull(result);
	}

}
