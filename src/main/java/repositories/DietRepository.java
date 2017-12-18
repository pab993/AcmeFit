
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

	@Query("select d from Diet d where d.nutritionist.id = ?1")
	Collection<Diet> findDietsByNutritionist(int nutritionistId);

	@Query("select d from Diet d join d.requestsDiets rd where rd.status = 'ACCEPTED' and rd.customer.id = ?1")
	Collection<Diet> findMyDiets(int customerId);
}
