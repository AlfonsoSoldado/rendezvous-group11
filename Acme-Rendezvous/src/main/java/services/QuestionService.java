package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Question;

@Service
@Transactional
public class QuestionService {

	// Managed repository
	
	@Autowired
	private QuestionRepository questionRepository;
	
	// Suporting services
	
	// Constructor
	
	public QuestionService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Question create(){
		Question res = new Question();
		
		res.setText("Question text");
		
		return res;
	}
	
	public Collection<Question> findAll(){
		Collection<Question> res;
		res = this.questionRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Question findOne(int questionId){
		Assert.isTrue(questionId != 0);
		Question res;
		res = this.questionRepository.findOne(questionId);
		Assert.notNull(res);
		return res;
	}
	
	public Question save(Question question) {
		Assert.notNull(question);
		Question res;
		res = this.questionRepository.save(question);
		return res;
	}
	
	public void delete(Question question) {
		Assert.notNull(question);
		Assert.isTrue(question.getId() != 0);
		Assert.isTrue(this.questionRepository.exists(question.getId()));
		this.questionRepository.delete(question);
	}

	
}
