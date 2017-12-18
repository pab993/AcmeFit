
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
public class CompetitionServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private CompetitionService	competitionService;


	// Tests
	// ====================================================

	/*
	 * Browse the catalogue of competitions (must include the corresponding
	 * information with these competitions)..
	 * 
	 * En este caso de uso se listan las competiciones que hay registradas en el
	 * sistema, siendo accesible para cualquier usuario autentificado o no.
	 */

	protected void listCompetitionTest(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.competitionService.findAll();
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void FindOneActivityTest(String username, int idCompetition, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			this.competitionService.findOne(idCompetition);

			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	// Drivers
	// ====================================================

	@Test
	public void driverListActivity() {

		Object testingData1[][] = {
			// Obtención de un listado de competiciones si estoy
			// autentificado como customer -> true
			{
				"customer1", null
			},
			// Obtención de un listado de competiciones si estoy
			// autentificado como trainer -> true
			{
				"trainer1", null
			},
			// Obtención de un listado de competiciones si estoy
			// autentificado como nutritionist -> true
			{
				"nutritionist1", null
			},
			// Obtención de un listado de competiciones si estoy
			// autentificado como un nutritionist que no existe -> false
			{
				"nutritionist22", IllegalArgumentException.class
			},
			// Obtención de un listado de competiciones si no estoy
			// autentificado -> true
			{
				null, null
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.listCompetitionTest((String) testingData1[i][0], (Class<?>) testingData1[i][1]);
	}

	@Test
	public void driverFindOneCompetitionTest() {

		Object testingData1[][] = {
			// Obtención de una competicion si estoy autentificado como
			// customer -> true
			{
				"customer1", 169, null
			},
			// Obtención de una competicion si no estoy autentificado ->
			// true
			{
				null, 169, null
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.FindOneActivityTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}
}
