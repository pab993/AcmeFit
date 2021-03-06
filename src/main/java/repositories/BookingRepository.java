
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("select b from Booking b where (b.status = 'CONFIRMED') and (b.moment between ?1 and ?2) and (b.service.id = ?3)")
	public Collection<Booking> findAllConfirmedBetweenMomentsByService(Date startMoment, Date endMoment, int serviceId);

	@Query("select b from Booking b where b.status = 'CONFIRMED' and b.service.id = ?1")
	public Collection<Booking> findAllConfirmedByService(int serviceId);

	@Query("select (select count(b1) * 1.0 from b b1 where b1.status = 'CANCELED')/count(b) from Booking b")
	public Collection<Object[]> ratioCanceledBookingsTotalBookings();

	@Query("select distinct (select count(b1) * 1.0 from b b1 where b1.status = 'CANCELED' and b1.withPenalty = true)/(select count(b2) * 1.0 from b b2 where b2.status = 'CANCELED') from Booking b")
	public Collection<Object[]> ratioCanceledBookingsWithPenaltyCanceledBookings();
}
