package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	@Query("select q from Question q join q.rendezvous r where r.id=?1")
	Question findQuestionByRendezvous(int rendezvousId);
	
}
