
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Transactional
@Service
public class ActorService {

	// Managed Repository 
	// ====================================================================================

	@Autowired
	private ActorRepository	actorRepository;


	// Supporting Services
	// ====================================================================================

	// Constructor methods
	// ====================================================================================

	public ActorService() {
		super();
	}

	// Simple CRUDS methods 
	// ====================================================================================

	public Actor findOne(final int id) {

		Assert.isTrue(this.actorRepository.exists(id));

		return this.actorRepository.findOne(id);
	}

	public Collection<Actor> findAll() {

		Collection<Actor> actors;

		actors = this.actorRepository.findAll();
		Assert.notEmpty(actors);

		return actors;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Assert.isInstanceOf(Actor.class, actor);
		Assert.isTrue(actor.getClass().equals(this.findByPrincipal().getClass()));

		final Actor saved = this.actorRepository.save(actor);

		return saved;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor, "Actor null");
		Assert.isTrue(this.actorRepository.exists(actor.getId()), "didn't found");

		this.actorRepository.delete(actor);
	}

	// Others bussines methods
	// ====================================================================================

	public Actor findByPrincipal() {
		Actor result;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			result = this.actorRepository.findByUserAccountId(userAccount.getId());

		} catch (final Throwable exc) {
			result = null;

		}
		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}


	// Reconstruct
	// ====================================================================================

	@Autowired
	Validator	validator;


	public Actor reconstruct(final Actor actor, final BindingResult binding) {
		// TODO Auto-generated method stub
		final Actor actorRec = this.findOne(actor.getId());

		actorRec.setName(actor.getName());
		actorRec.setSurname(actor.getSurname());
		actorRec.setAddress(actor.getAddress());
		actorRec.setEmail(actor.getEmail());
		actorRec.setPhone(actor.getPhone());
		actorRec.setPicture(actor.getPicture());

		this.validator.validate(actorRec, binding);

		return actorRec;
	}
}
