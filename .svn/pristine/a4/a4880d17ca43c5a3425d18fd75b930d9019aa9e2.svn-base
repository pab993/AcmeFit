
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComentableRepository;
import domain.Activity;
import domain.Comentable;
import domain.Trainer;

@Transactional
@Service
public class ComentableService {

	//Repository
	//======================================================================

	@Autowired
	private ComentableRepository		comentableRepository;

	//Services
	// ======================================================================


	//CRUD methods
	//=======================================================================
	
	public Comentable findOneAux(int comentableId){
		Assert.isTrue(comentableId != 0);
		Comentable result;
		
		result = comentableRepository.findOneAux(comentableId);
		return result;
	}
	
	
	public Comentable findOne(int comentableId) {
		Assert.isTrue(comentableId != 0);
		Comentable result;
		
		result = comentableRepository.findOne(comentableId);
		return result;
	}
	
	public Collection<Comentable> findAll() {
		Collection<Comentable> result;

		result = comentableRepository.findAll();

		return result;
	}

	//Other bussiness methods
	// ==================================================================

}
