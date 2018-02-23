
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
	
	@Query("select r.announcement from Rendezvous r where r.id=?1")
	Collection<Announcement> findAnnouncementsByRendezvous(int id);

}
