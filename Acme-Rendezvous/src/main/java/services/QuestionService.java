
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class QuestionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private QuestionRepository	questionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AnswerService		answerService;

	@Autowired
	private UserService			userService;

	// Constructor ------------------------------------------------------------

	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Question create() {
		final Question res = new Question();
		res.setAnswer(new ArrayList<Answer>());
		res.setText("Question text");
		return res;
	}

	public Collection<Question> findAll() {
		Collection<Question> res;
		res = this.questionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Question findOne(final int questionId) {
		Assert.isTrue(questionId != 0);
		Question res;
		res = this.questionRepository.findOne(questionId);
		Assert.notNull(res);
		return res;
	}

	public Question save(final Question question) {
		Assert.notNull(question);
		if(question.getId() != 0){
			Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
			User user;
			user = userService.findByPrincipal();
			rendezvouses.addAll(user.getRendezvous());
			Assert.isTrue(rendezvouses.contains(question.getRendezvous()));
		}
		Question res;
		res = this.questionRepository.save(question);
		return res;
	}

	public void delete(final Question question) {
		Assert.notNull(question);
		Assert.isTrue(question.getId() != 0);
		Assert.isTrue(this.questionRepository.exists(question.getId()));

		Collection<Answer> answers;
		final int questionId = question.getId();
		answers = this.answerService.findAnswersByQuestion(questionId);
		for (final Answer res : answers){
			this.answerService.delete(res);
		}
		
		User user;
		user = userService.findUserByQuestion(question.getId());
		Collection<Question> questions = new ArrayList<Question>();
		questions.addAll(user.getQuestion());
		questions.remove(question);
		user.setQuestion(questions);

		this.questionRepository.delete(question);
	}

	
	public Collection<Question> findQuestionByRendezvous(final int rendezvousId) {
		Collection<Question> result;
		result = this.questionRepository.findQuestionByRendezvous(rendezvousId);
		Assert.notNull(result);

		return result;
	}
}
