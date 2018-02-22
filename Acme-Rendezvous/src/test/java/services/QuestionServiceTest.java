
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class QuestionServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private AnswerService		answerService;


	// Tests --------------------

	@Test
	public void test() {

		final Question question = this.questionService.create();
		final Rendezvous rendezvous = this.rendezvousService.findOne(27);
		final String text = "text";
		question.setText(text);
		question.setRendezvous(rendezvous);

		Question saved = this.questionService.save(question);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.questionService.findOne(id));
		saved.setText("updated");

		final Answer answer = this.answerService.create();
		answer.setText("text");
		answer.setQuestion(saved);
		final Answer savedAnswer = this.answerService.save(answer);
		Assert.isTrue(this.answerService.findOne(savedAnswer.getId()) != null);

		saved = this.questionService.save(saved);
		Assert.isTrue(this.questionService.findOne(saved.getId()).getText() == "updated");
		Assert.isTrue(this.questionService.findAll().contains(saved));
		this.questionService.delete(saved);
		Assert.isTrue(!this.questionService.findAll().contains(saved));
		Assert.isTrue(!this.answerService.findAll().contains(savedAnswer));
	}
}
