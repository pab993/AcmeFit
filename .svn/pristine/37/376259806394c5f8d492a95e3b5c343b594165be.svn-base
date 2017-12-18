
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AdditionalService;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Integer> {

	@Query("select b.service from Booking b where b.status = 'CONFIRMED' group by b.service having count(b) >= all(select count(b1) from Booking b1 where b1.status = 'CONFIRMED' group by b1.service)")
	public Collection<AdditionalService> servicesWithMoreConfirmedBookings();

	@Query("select min(s.bookings.size), avg(s.bookings.size), max(s.bookings.size) from AdditionalService s")
	public Collection<Object[]> minAvgMaxBookingsPerService();

}
