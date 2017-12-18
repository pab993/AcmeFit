
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	@Query("select c from Curriculum c where c.employee.userAccount.id = ?1")
	Curriculum findByUserAccount(int userAccountId);

	@Query("select c from Curriculum c where c.employee.id = ?1")
	Curriculum findByEmployeeId(int employeeID);
}
