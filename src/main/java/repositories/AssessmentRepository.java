
package repositories;

import java.util.Collection;

import javax.validation.constraints.Max;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

	@Query("select a from Assessment a where a.actor.id=?1")
	Collection<Assessment> findAllByActor(int actorId);
	@Max(4)
	@Query("select a from Assessment a group by a.stars")
	Collection<Assessment> find4Assessments();
}
