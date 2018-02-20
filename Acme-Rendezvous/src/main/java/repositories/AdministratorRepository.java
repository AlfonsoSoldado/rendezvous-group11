package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	
	@Query("select avg(m.rendezvous.size) from User m")
	int averageRedezvousUser();
	
	@Query("select  stddev(m.rendezvous.size) from User m")
	int EstandardDesviationRedezvousUser();

	@Query("select (select count(a) from Application a where a.status='PENDING')/count(ap)*1.0 from Application ap")
	int ratioCreateAndNoCreateRendezvousUser();

	select (select count(a) from User a where a.rendezvous.size>0)/count(ap)*1.0 from User ap union (select count(b) from User b where b.rendezvous.size=0)/count(c)*1.0 from User c;	
	// Simple CRUD methods ----------------------------------------------------

}

