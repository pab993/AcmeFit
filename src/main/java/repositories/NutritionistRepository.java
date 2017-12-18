
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Nutritionist;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {

	//Busca un Nutritionist por su userAccount id
	@Query("select n from Nutritionist n where n.userAccount.id = ?1")
	Nutritionist findByUserAccountId(int userAccountId);
	
	@Query("select n from Nutritionist n join n.diets d order by count(d.requestsDiets.size)")
	Collection<Nutritionist> findOrderByPopularity();

}
