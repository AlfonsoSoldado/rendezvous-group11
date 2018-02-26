package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Question;

@Service
@Transactional
public class AnswerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private Validator				validator;

	// Constructor ------------------------------------------------------------

	public AnswerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Answer create() {
		Answer result;
		result = new Answer();
		return result;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result;
		result = this.answerRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Answer findOne(final int answerId) {
		Answer result;
		result = this.answerRepository.findOne(answerId);
		return result;
	}

	public Answer save(final Answer answer) {
		Answer result = answer;
		Assert.notNull(answer);
		result = this.answerRepository.save(result);
		return result;
	}

	public void delete(final Answer answer) {
		Assert.notNull(answer);
		Assert.isTrue(answer.getId() != 0);
		this.answerRepository.delete(answer);
	}

	// Other business method --------------------------------------------------

	public Collection<Answer> findAnswersByQuestion(int id) {
		Collection<Answer> res;
		res = this.answerRepository.findAnswersByQuestion(id);
		return res;
	}
	
//	public Answer reconstruct(final Answer answer, final BindingResult binding) {
//		Answer res;
//		Answer answerFinal;
//		if (answer.getId() == 0) {
//			Question question;
//
//			question = new Question();
//
//			answer.setQuestion(question);
//			res = answer;
//		} else {
//			answerFinal = this.answerRepository.findOne(answer.getId());
//			answer.setQuestion(answerFinal.getQuestion());
//			res = answer;
//		}
//		this.validator.validate(res, binding);
//		return res;
//	}

}
