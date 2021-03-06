
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RequestTraining;

@Repository
public interface RequestTrainingRepository extends JpaRepository<RequestTraining, Integer> {

	@Query("select r from RequestTraining r where r.status = 'PENDING' and r.customer.id = ?1")
	public Collection<RequestTraining> findAllPendingByCustomer(int customerId);

	@Query("select r from RequestTraining r where r.customer.id = ?1 and (r.status = 'ACCEPTED' or r.status = 'DENIED')")
	public Collection<RequestTraining> findAllByCustomer(int customerId);

	@Query("select rp from RequestTraining rp where rp.customer.id = ?1 and rp.personalTraining.id = ?2")
	RequestTraining findByCustomerAndPersonalTraining(int customerId, int personalTrainingId);

	@Query("select r from RequestTraining r where r.personalTraining.trainer.id = ?1")
	Collection<RequestTraining> findAllByTrainer(int trainerId);
}
