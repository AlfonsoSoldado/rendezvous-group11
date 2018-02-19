
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CommentServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private CommentService	commentService;


	// Tests --------------------

	@Test
	public void test() {

		final Comment comment = this.commentService.create();

		final String text = "text";
		final String picture = "http://www.foto.com";
		comment.setText(text);
		comment.setPicture(picture);

		Comment saved = this.commentService.save(comment);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.commentService.findOne(id));
		saved.setText("updated");
		saved = this.commentService.save(saved);
		Assert.isTrue(this.commentService.findOne(saved.getId()).getText() == "updated");
		Assert.isTrue(this.commentService.findAll().contains(saved));
		this.commentService.delete(saved);
		Assert.isTrue(!this.commentService.findAll().contains(saved));
	}
}
