
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	text;


	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	//Relationships -----------------------------------------------------------

	private Collection<Answer> answer;
	
	@OneToMany(mappedBy = "question")
	public Collection<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(Collection<Answer> answer) {
		this.answer = answer;
	}

}
