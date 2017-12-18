
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdditionalServiceRepository;
import domain.AdditionalService;
import domain.Administrator;
import domain.Assessment;
import domain.Booking;

@Transactional
@Service
public class AdditionalServiceService {

	// Managed Repository 
	// ====================================================================================

	@Autowired
	private AdditionalServiceRepository	additionalServiceRepository;

	//Services
	// ======================================================================

	@Autowired
	private Validator					validator;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private BookingService				bookingService;


	// Constructor methods
	// ====================================================================================

	public AdditionalServiceService() {
		super();
	}

	// Simple CRUDS methods 
	// ====================================================================================

	public AdditionalService create() {
		AdditionalService service;

		Assert.isInstanceOf(Administrator.class, this.actorService.findByPrincipal());

		service = new AdditionalService();
		service.setAssessments(new ArrayList<Assessment>());
		service.setBookings(new ArrayList<Booking>());
		service.setDuration(5);
		service.setMaximumCapacity(1);

		return service;
	}

	public AdditionalService findOne(int id) {
		AdditionalService service;

		Assert.notNull(id);
		service = this.additionalServiceRepository.findOne(id);
		Assert.notNull(service);

		return service;
	}

	public Collection<AdditionalService> findAll() {
		Collection<AdditionalService> services;

		services = this.additionalServiceRepository.findAll();
		Assert.notNull(services);

		return services;
	}

	public AdditionalService save(AdditionalService service) {
		AdditionalService serviceSaved;

		Assert.notNull(service);
		Assert.isInstanceOf(Administrator.class, this.actorService.findByPrincipal());
		Assert.isTrue(service.getOpeningTime().before(service.getClosingTime()));

		serviceSaved = this.additionalServiceRepository.save(service);
		Assert.notNull(serviceSaved);

		return serviceSaved;
	}

	public void delete(AdditionalService service) {
		Assert.notNull(service);
		Assert.isTrue(this.additionalServiceRepository.exists(service.getId()));
		Assert.isInstanceOf(Administrator.class, this.actorService.findByPrincipal());
		Assert.isTrue(this.bookingService.findAllConfirmedByService(service).size() == 0);

		this.additionalServiceRepository.delete(service);
	}

	public Collection<AdditionalService> servicesWithMoreConfirmedBookings() {
		return this.additionalServiceRepository.servicesWithMoreConfirmedBookings();
	}

	public Collection<Object[]> minAvgMaxBookingsPerService() {
		return this.additionalServiceRepository.minAvgMaxBookingsPerService();
	}

	// Others bussines methods
	// ====================================================================================

	public AdditionalService reconstruct(AdditionalService service, BindingResult binding) {
		AdditionalService serviceReconstructed;

		if (service.getId() == 0)
			serviceReconstructed = this.create();
		else
			serviceReconstructed = this.findOne(service.getId());

		serviceReconstructed.setName(service.getName());
		serviceReconstructed.setOpeningTime(service.getOpeningTime());
		serviceReconstructed.setClosingTime(service.getClosingTime());
		serviceReconstructed.setDuration(service.getDuration());
		serviceReconstructed.setCost(service.getCost());
		serviceReconstructed.setMaximumCapacity(service.getMaximumCapacity());

		if ((service.getOpeningTime() != null) && (service.getClosingTime() != null))
			if (!serviceReconstructed.getClosingTime().after(serviceReconstructed.getOpeningTime()))
				binding.rejectValue("closingTime", "service.closingTime.noValid");

		this.validator.validate(serviceReconstructed, binding);
		Assert.isTrue(!binding.hasErrors());

		return serviceReconstructed;
	}
}
