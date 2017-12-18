
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RequestTrainingServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private RequestTrainingService	requestTrainingService;


	// Tests
	// ====================================================

	/*
	 * Cancel his or her request. A request can be cancelled if the status is PENDING.
	 * 
	 * En este caso de uso se cancelan las peticiones de entrenamientos personales registradas en el sistema, perteneciendo estas peticiones al customer registrado en ese momento. Por lo tanto, tan sólo es accesible para los customers.
	 * Para provocar el error, introduciremos actores que no tienen permiso para realizar dicha cancelación, así como actores y peticiones de entrenamientos personales cuyos Ids no existen.
	 */

	protected void cancelRequestTrainingTest(String username, int requestTrainingId, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.requestTrainingService.cancelled(requestTrainingId);
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	// Drivers
	// ====================================================

	@Test
	public void driverCancelRequestTraining() {

		Object testingData1[][] = {
			// Cancelación de una petición de entrenamiento personal si estoy autentificado como customer -> true
			{
				"customer1", 159, null
			},
			// Cancelación de una petición de entrenamiento personal si estoy autentificado como customer cuya petición no existe-> false		
			{
				"customer1", 140, IllegalArgumentException.class
			},
			// Cancelación de una petición de entrenamiento personal si estoy autentificado como nutritionist -> false		
			{
				"nutritionist1", 159, IllegalArgumentException.class
			},
			// Cancelación de una petición de entrenamiento personal si estoy autentificado como un trainer que no existe -> false				
			{
				"customer22", 159, IllegalArgumentException.class
			},
			// Cancelación de una petición de entrenamiento personal si no estoy autentificado  -> true				
			{
				null, 159, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.cancelRequestTrainingTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

}
