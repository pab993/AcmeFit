
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//Busca un actor por su userAccount id
	@Query("select e from Employee e where e.userAccount.id = ?1")
	Employee findByUserAccountId(int userAccountId);
}
