package repositories;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Rendezvous;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	
	@Query("select avg(m.rendezvous.size) from User m")
	double averageRedezvousUser();
	
	@Query("select  stddev(m.rendezvous.size) from User m")
	double EstandardDesviationRedezvousUser();

	@Query("select (select count(a) from User a where a.rendezvous.size>=1)/count(ap)*1.0 from User ap;")
	double ratioCreateAndNoCreateRendezvousUser();

	
	@Query("select (select count(a) from User a where a.rendezvous.size>0)/count(ap)*1.0 from User ap")
	double ratioUserConRendezvous();

	@Query("select (select count(b) from User b where b.rendezvous.size=0)/count(c)*1.0 from User c")
	double ratioUserSinRendezvous();
	
	
	@Query("select avg(m.attendant.size) from Rendezvous m")
	double averageUsersRendezvous();

	@Query("select  stddev(m.attendant.size) from Rendezvous m")
	double estandardDesviationUsersRendezvous();
	
	@Query("select avg(m.rsvp.size) from User m join m.rsvp e where e.confirmed=true")
	double averageRendezvousRSVPTruePerUser();
	
	@Query("select stddev(m.rsvp.size) from User m join m.rsvp e where e.confirmed=true")
	double estandardDesviationRendezvousRSVPTruePerUser();
	
	
	@Query("select m.rendezvous from User m join m.rsvp e where e.confirmed=true order by m.rsvp.size")
	Collection<Rendezvous> topRendezvous(Pageable a);
	
	//revisar
	@Query("select count(e.announcement)*1.0/(select count(r) from Rendezvous r)*1.0 from User m join m.rendezvous e where e.announcement!=null")
	double averageannouncementsUser();
	
	@Query("select m from Rendezvous m where m.similar.size > (select avg(v.similar.size)*1.1 from Rendezvous v)")
	double redezvousSimiliars10();
	
	@Query("select avg(m.question.size) from Rendezvous m")
	double averageNumberOfQuestionsPerRendezvous();
	
	@Query("select stddev(m.question.size) from Rendezvous m")
	double estandardDesviationOfQuestionsPerRendezvous();
	
	//revisar
	@Query("select count(m.answer.size)/(select count(f) from Rendezvous f)*1.0 from Question m")
	double averageOfAnswerPerQuestionsPerRendezvous();
	
	@Query("select stddev((m.answer.size)/(select count(f) from Rendezvous f)*1.0) from Question m")
	double estandardDesviationOfAnswerPerQuestionsPerRendezvous();
	
	/*
	 * preguntar a muller si es más eficiente sacar la media y la desviacion en una sola consulta o en varias
	 * 
	 * The average and the standard deviation of announcements per rendezvous. revisar
 The rendezvouses that whose number of announcements is above 75% the average number of announcements per rendezvous. revisar

 T

 The average and the standard deviation of the number of answers to the questions per rendezvous.
 The average and the standard deviation of replies per comment
	 * */
	
	
}

