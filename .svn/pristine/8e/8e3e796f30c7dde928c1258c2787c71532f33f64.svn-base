
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActorServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ActorService	actorService;


	// Tests
	// ====================================================

	/*
	 * Edit the information of his or her profile.
	 * 
	 * En este caso de uso editaremos la información del perfil de un actor.
	 * Para provocar el error, introduciremos atributos que no cumplen con las restricciones y/o atributos no válidos.
	 */

	public void editTest(final String username, final int actorId, final String email, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			final Actor actor = this.actorService.findOne(actorId);

			this.authenticate(username);
			actor.setEmail(email);
			this.actorService.save(actor);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ====================================================

	@Test
	public void driverEdit() {

		Object testingData[][] = {
			// Editar un actor con exito
			{
				"nutritionist1", 139, "emailnuevo@emailnuevo.com", null
			},
			// Editar un actor sin estar autenticado
			{
				null, 139, "emailnuevo@emailnuevo.com", NullPointerException.class
			},
			// Editar un actor con un id no valido
			{
				"nutritionist1", 10, "emailnuevo@emailnuevo.com", IllegalArgumentException.class
			},
			// Editar un actor con un id no valido
			{
				"nutritionist1", 10, "email no valido", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.editTest((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);

		testingData = null;

	}

}
