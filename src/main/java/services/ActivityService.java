
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import domain.Activity;
import domain.Customer;
import domain.Invoice;
import domain.RegisterFor;
import domain.Trainer;

@Transactional
@Service
public class ActivityService {

	//Repository
	//======================================================================

	@Autowired
	private ActivityRepository	activityRepository;
	@Autowired
	private TrainerService		trainerService;

	//Services
	// ======================================================================

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private RegisterForService	registerForService;


	//CRUD methods
	//=======================================================================

	public Activity findOne(final int id) {
		Activity activity;

		activity = this.activityRepository.findOne(id);
		Assert.notNull(activity);

		return activity;
	}
	public Activity save(Activity activity) {

		Assert.notNull(activity);
		activity.setRegistersFor(new HashSet<RegisterFor>());
		activity.setTrainer(trainerService.findByPrincipal());
		return activityRepository.save(activity);
	}
	public Activity create() {
		final Activity activity = new Activity();
		Assert.isInstanceOf(Trainer.class, this.actorService.findByPrincipal());
		activity.setCurrentAttendance(0);

		return activity;
	}

	public Collection<Activity> findAll() {

		Collection<Activity> activities;

		activities = this.activityRepository.findAll();
		Assert.notNull(activities);

		return activities;
	}

	//Other bussiness methods
	// ==================================================================

	public Collection<Activity> search(final String keyword) {
		Collection<Activity> activities;

		activities = this.activityRepository.search(keyword);
		Assert.notNull(activities);

		return activities;
	}

	public Collection<Activity> findAllByCustomerId(final int customerId) {

		Collection<Activity> activities;

		activities = this.activityRepository.findAllByCustomerId(customerId);
		Assert.notNull(activities);

		return activities;
	}

	public void signUp(int activiyId) {
		// TODO Auto-generated method stub

		Customer principal = customerService.findByPrincipal();
		Activity activity = activityRepository.findOne(activiyId);

		Assert.isTrue(checkPrincipalIsNotRegisterYet(principal, activity), "usuario ya registrado");
		Assert.isTrue(activity.getCurrentAttendance() < activity.getMaximumAttendance());
		Assert.isTrue(checkAllInvoices(principal));

		// crear el registro

		RegisterFor registerFor = new RegisterFor();

		registerFor.setActivity(activity);
		registerFor.setCustomer(principal);

		// Actualizar cliente

		Collection<RegisterFor> registerFors = principal.getRegistersFor();
		registerFors.add(registerFor);
		principal.setRegistersFor(registerFors);

		// Actualizar actividad

		int actual = activity.getCurrentAttendance();
		activity.setCurrentAttendance(actual + 1);
		Collection<RegisterFor> registerFors2 = activity.getRegistersFor();
		registerFors2.add(registerFor);
		activity.setRegistersFor(registerFors2);

		// Aplico cambios

		registerForService.save(registerFor);
		customerService.save(principal);
		activityRepository.save(activity);
	}

	private boolean checkAllInvoices(Customer principal) {
		// TODO Auto-generated method stub

		boolean resul = true;

		if (!principal.getInvoices().isEmpty()) {

			for (Invoice invoice : principal.getInvoices()) {
				if (!invoice.getPaid()) {
					resul = false;
					break;
				}
			}
		}

		return resul;
	}
	private boolean checkPrincipalIsNotRegisterYet(Customer principal, Activity activity) {

		boolean resul = true;

		if (!principal.getRegistersFor().isEmpty()) {
			Collection<RegisterFor> registerFors = principal.getRegistersFor();

			for (RegisterFor registerFor : registerFors) {
				if (registerFor.getActivity().getId() == activity.getId()) {
					resul = false;
					break;

				}

			}
		}

		return resul;
	}
}
