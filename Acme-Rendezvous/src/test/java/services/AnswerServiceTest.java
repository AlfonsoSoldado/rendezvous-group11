
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AnswerServiceTest extends AbstractTest {

	// Service under test --------------------

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private QuestionService	questionService;


	// Tests --------------------

	@Test
	public void test() {

		final Answer answer = this.answerService.create();
		final Question question = this.questionService.findOne(37);
		final String text = "text";
		answer.setText(text);
		answer.setQuestion(question);

		Answer saved = this.answerService.save(answer);
		final int id = saved.getId();
		Assert.isTrue(id != 0);
		Assert.notNull(this.answerService.findOne(id));
		saved.setText("updated");
		saved = this.answerService.save(saved);
		Assert.isTrue(this.answerService.findOne(saved.getId()).getText() == "updated");
		Assert.isTrue(this.answerService.findAll().contains(saved));
		Assert.isTrue(this.answerService.findAnswersByQuestion(37).contains(saved));
		this.answerService.delete(saved);
		Assert.isTrue(!this.answerService.findAll().contains(saved));

	}
}
