
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	//Busca un Trainer por su userAccount id
	@Query("select a from Trainer a where a.userAccount.id = ?1")
	Trainer findByUserAccountId(int userAccountId);

	//Esta query se usa porque por alguna raz�n falla en el covertidor el findOne normal
	@Query("select a from Trainer a where a.id=?1")
	Trainer findOne2(int trainerId);

	//The minimum, the average, and the maximum number of activities per trainer
	@Query("select min(t.activities.size) from Trainer t")
	Double minActivitiesTrainer();

	@Query("select avg(t.activities.size) from Trainer t")
	Double avgActivitiesTrainer();

	@Query("select max(t.activities.size) from Trainer t")
	Double maxActivitiesTrainer();

	//The trainer/s who has/have taught more activities
	@Query("select t from Trainer t where t.activities.size >= all(select t.activities.size from Trainer t)")
	Collection<Trainer> findTrainersMoreActivities();

	//The trainer/s who has/have taught less activities
	@Query("select t from Trainer t where t.activities.size <= all(select t.activities.size from Trainer t)")
	Collection<Trainer> findTrainersLessActivities();

	//The ranking of trainer according the personal trainings that he or she has done
	@Query("select t from Trainer t order by t.personalTrainings.size")
	Collection<Trainer> findTrainersOrderByPersonalTrainings();
}
