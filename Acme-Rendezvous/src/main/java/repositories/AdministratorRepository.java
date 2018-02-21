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

	
	@Query("select avg(m.rendezvous.size*1.0) from User m")
	double averageRedezvousUser();
	
	@Query("select  stddev(m.rendezvous.size*1.0) from User m")
	double EstandardDesviationRedezvousUser();

	@Query("select (select count(a) from User a where a.rendezvous.size>=1)/count(ap)*1.0 from User ap;")
	double ratioCreateAndNoCreateRendezvousUser();

	
	@Query("select (select count(a) from User a where a.rendezvous.size>0)/count(ap)*1.0 from User ap")
	double ratioUserConRendezvous();

	@Query("select (select count(b) from User b where b.rendezvous.size=0)/count(c)*1.0 from User c")
	double ratioUserSinRendezvous();
	
	
	@Query("select avg(m.attendant.size*1.0) from Rendezvous m")
	double averageUsersRendezvous();

	@Query("select stddev(m.attendant.size*1.0) from Rendezvous m")
	double estandardDesviationUsersRendezvous();
	
	@Query("select avg(m.rsvp.size*1.0) from User m join m.rsvp e where e.confirmed=true")
	double averageRendezvousRSVPTruePerUser();
	
	@Query("select stddev(m.rsvp.size*1.0) from User m join m.rsvp e where e.confirmed=true")
	double estandardDesviationRendezvousRSVPTruePerUser();
	
	
	@Query("select m.rendezvous from User m join m.rsvp e where e.confirmed=true order by m.rsvp.size")
	Collection<Rendezvous> topRendezvous(Pageable a);
	
	@Query("select avg(e.announcement.size*1.0) from Rendezvous e")
	double averageAnnouncementsRendezvous();
	
	@Query("select stddev(e.announcement.size*1.0) from Rendezvous e")
	double estandardDesviationAnnouncementsUser();
	
	
	@Query("select m from Rendezvous m where m.similar.size > (select avg(v.similar.size)*1.1 from Rendezvous v)")
	double redezvousSimiliars10();
	
	//revisar
	@Query(" select ((count(m)*1.0)/(select count(h) from Rendezvous h))from Question m where m.rendezvous.id in (select r.id from Rendezvous r)")
	double averageNumberOfQuestionsPerRendezvous();
	
	//revisar
	@Query("select stddev(m.question.size*1.0) from Rendezvous m")
	double estandardDesviationOfQuestionsPerRendezvous();
	
	//revisar
	@Query("select count(m.answer.size)/(select count(f) from Rendezvous f)*1.0 from Question m")
	double averageOfAnswerPerQuestionsPerRendezvous();
	//revisar
	@Query("select stddev((m.answer.size*1.0)/(select count(f) from Rendezvous f)*1.0) from Question m")
	double estandardDesviationOfAnswerPerQuestionsPerRendezvous();
	
	
	
	
	@Query("select r from Rendezvous r where r.announcement.size >(select avg(n.announcement.size)*0.75 from Rendezvous n)")
	Collection<Rendezvous> RendezvousConMas075Announcement();
	
	@Query("select avg(m.replies.size) from Comment m")
	double averageRepliesComment();
	
	@Query("select stddev(m.replies.size) from Comment m")
	double estandardDesviationRepliesComment();
	
	/*
	 * preguntar a muller si es m�s eficiente sacar la media y la desviacion en una sola consulta o en varias
	 * 
	 * The average and the standard deviation of announcements per rendezvous. revisar
	 * 

	 * */
	
}

