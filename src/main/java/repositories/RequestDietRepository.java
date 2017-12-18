
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RequestDiet;

@Repository
public interface RequestDietRepository extends JpaRepository<RequestDiet, Integer> {

	@Query("select rd from RequestDiet rd where rd.customer.id = ?1 and rd.diet.id = ?2")
	RequestDiet findByCustomerAndDiet(int customerId, int dietId);

	@Query("select r from RequestDiet r where r.status = 'PENDING' and r.customer.id = ?1")
	public Collection<RequestDiet> findAllPendingByCustomer(int customerId);

	@Query("select r from RequestDiet r where r.customer.id = ?1 and (r.status = 'ACCEPTED' or r.status = 'DENIED')")
	public Collection<RequestDiet> findAllByCustomer(int customerId);

	@Query("select r from RequestDiet r where r.customer.id = ?1 and r.status = 'ACCEPTED'")
	public Collection<RequestDiet> findAllAcceptedByCustomer(int customerId);

	@Query("select rd from RequestDiet rd where rd.diet.nutritionist.id = ?1")
	Collection<RequestDiet> findRequestsByNutritionist(int nutritionistId);

}
