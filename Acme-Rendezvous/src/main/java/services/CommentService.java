
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;


	// Constructor

	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comment create() {
		Comment result;

		result = new Comment();

		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);

		return result;
	}

	public Comment save(final Comment comment) {
		Comment result = comment;
		Assert.notNull(comment);

		if (comment.getId() == 0) {
			Date momentMade;
			momentMade = new Date(System.currentTimeMillis() - 1000);
			result.setMomentMade(momentMade);
		}

		result = this.commentRepository.save(result);

		return result;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		this.commentRepository.delete(comment);
	}
}
