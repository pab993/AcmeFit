
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.AdditionalServiceService;
import services.BookingService;
import services.ConfigurationSystemService;
import services.CustomerService;
import services.NutritionistService;
import services.PersonalTrainingService;
import services.RequestDietService;
import services.TrainerService;
import controllers.AbstractController;
import domain.AdditionalService;
import domain.Customer;
import domain.Nutritionist;
import domain.Trainer;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}


	// Services
	// ========================================================================
	@Autowired
	private ActivityService				activityService;

	@Autowired
	private RequestDietService			requestDietService;

	@Autowired
	private CustomerService				customerService;

	@Autowired
	private TrainerService				trainerService;
	@Autowired
	private ConfigurationSystemService configurationSystemService;

	@Autowired
	private BookingService				bookingService;

	@Autowired
	private AdditionalServiceService	additionalServiceService;
	
	@Autowired 
	private NutritionistService nutritionistService;
	
	@Autowired
	private PersonalTrainingService personalTrainingService;

	// DashBoard
	// ============================================================================

	@RequestMapping(value = "/showAssessments", method = RequestMethod.GET)
	public ModelAndView showAssessments() {
		ModelAndView result;
		

		try {
			configurationSystemService.changeShowAssessments();
			result = new ModelAndView("redirect:../welcome/index.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../welcome/index.do");
		}
		
		
		return result;
		
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		String minAvgMaxActivitiesTrainer = "";
		String minAvgMaxDietsByCustomer = "";
		
		String avgPersonalTrainingForCustomer = "";

		Collection<Trainer> findTrainersMoreActivities = new ArrayList<Trainer>();
		Collection<Trainer> findTrainersOrderByPersonalTrainings = new ArrayList<Trainer>();
		Collection<Trainer> findTrainersLessActivities = new ArrayList<Trainer>();

		Collection<Customer> rankingCustomersByInvoices = new ArrayList<Customer>();
		Collection<Customer> findCustomerSpentMore70 = new ArrayList<Customer>();
		Collection<Customer> findCustomerMoreActivities = new ArrayList<Customer>();
		Collection<Customer> findCustomerLessActivities = new ArrayList<Customer>();

		Collection<Customer> customersWithMoreCanceledBookings = this.customerService.customerWithMoreCanceledBookings();
		Collection<Object[]> minAvgMaxBookingsPerService = this.additionalServiceService.minAvgMaxBookingsPerService();
		Collection<AdditionalService> servicesWithMorConfirmedBookings = this.additionalServiceService.servicesWithMoreConfirmedBookings();
		Collection<Object[]> ratioCanceledBookingsTotalBookings = this.bookingService.ratioCanceledBookingsTotalBookings();
		Collection<Object[]> ratioCanceledBookingsWPCanceledBookings = this.bookingService.ratioCanceledBookingsWithPenaltyCanceledBookings();

		Collection<Nutritionist> findOrderByPopularity = new ArrayList<Nutritionist>();
		
		if (this.activityService.findAll().isEmpty() || this.trainerService.findAll().isEmpty())
			minAvgMaxActivitiesTrainer = "Not available / No disponible";
		else
			minAvgMaxActivitiesTrainer = this.trainerService.minAvgMaxActivitiesTrainer();

		if (this.requestDietService.findAll().isEmpty() || this.customerService.findAll().isEmpty())
			minAvgMaxDietsByCustomer = "Not available / No disponible";
		else
			minAvgMaxDietsByCustomer = this.customerService.minAvgMaxDietsByCustomer();

		if (this.trainerService.findAll().isEmpty())
			findTrainersMoreActivities = new ArrayList<Trainer>();
		else
			findTrainersMoreActivities = this.trainerService.findTrainersMoreActivities();

		if (this.trainerService.findAll().isEmpty())
			findTrainersLessActivities = new ArrayList<Trainer>();
		else
			findTrainersLessActivities = this.trainerService.findTrainersLessActivities();

		if (this.trainerService.findAll().isEmpty())
			findTrainersOrderByPersonalTrainings = new ArrayList<Trainer>();
		else
			findTrainersOrderByPersonalTrainings = this.trainerService.findTrainersOrderByPersonalTrainings();

		if (this.customerService.findAll().isEmpty())
			rankingCustomersByInvoices = new ArrayList<Customer>();
		else
			rankingCustomersByInvoices = this.customerService.rankingCustomersByInvoices();

		if (this.customerService.findAll().isEmpty())
			findCustomerSpentMore70 = new ArrayList<Customer>();
		else
			findCustomerSpentMore70 = this.customerService.findCustomerSpentMore70();

		if (this.customerService.findAll().isEmpty())
			findCustomerMoreActivities = new ArrayList<Customer>();
		else
			findCustomerMoreActivities = this.customerService.findCustomerMoreActivities();

		if (this.customerService.findAll().isEmpty())
			findCustomerLessActivities = new ArrayList<Customer>();
		else
			findCustomerLessActivities = this.customerService.findCustomerLessActivities();

		result = new ModelAndView("administrator/dashboard");
		
		if(this.nutritionistService.findAll().isEmpty())
			findOrderByPopularity = new ArrayList<Nutritionist>();
		else
			findOrderByPopularity = this.nutritionistService.findOrderByPopularity();
		
		if(this.personalTrainingService.findAll().isEmpty() || this.customerService.findAll().isEmpty())
			avgPersonalTrainingForCustomer = "Not available / No disponible";
		else
			avgPersonalTrainingForCustomer = this.personalTrainingService.avgPersonalTrainingForCustomer().toString();
			

		result.addObject("minAvgMaxActivitiesTrainer", minAvgMaxActivitiesTrainer);
		result.addObject("minAvgMaxDietsByCustomer", minAvgMaxDietsByCustomer);
		result.addObject("findTrainersMoreActivities", findTrainersMoreActivities);
		result.addObject("findTrainersOrderByPersonalTrainings", findTrainersOrderByPersonalTrainings);
		result.addObject("findfindTrainersLessActivities", findTrainersLessActivities);

		result.addObject("rankingCustomersByInvoices", rankingCustomersByInvoices);
		result.addObject("findCustomerSpentMore70", findCustomerSpentMore70);
		result.addObject("findfindCustomerMoreActivities", findCustomerMoreActivities);
		result.addObject("findfindCustomerLessActivities", findCustomerLessActivities);

		result.addObject("customersWithMoreCanceledBookings", customersWithMoreCanceledBookings);
		result.addObject("minAvgMaxBookingsPerService", minAvgMaxBookingsPerService);
		result.addObject("servicesWithMorConfirmedBookings", servicesWithMorConfirmedBookings);
		result.addObject("ratioCanceledBookingsTotalBookings", ratioCanceledBookingsTotalBookings);
		result.addObject("ratioCanceledBookingsWPCanceledBookings", ratioCanceledBookingsWPCanceledBookings);
		
		result.addObject("nutritionistOrderByPopularity", findOrderByPopularity);
		result.addObject("avgPersonalTrainingForCustomer", avgPersonalTrainingForCustomer);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}
}
