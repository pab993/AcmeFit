
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	//Busca un actor por su userAccount id
	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccountId);

	//Esta query se usa porque por alguna razón falla en el covertidor el findOne normal
	@Query("select a from Actor a where a.id=?1")
	Actor findOne2(int actorId);
}
