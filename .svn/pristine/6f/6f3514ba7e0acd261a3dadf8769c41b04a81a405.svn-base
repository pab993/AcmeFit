
package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BookingRepository;
import domain.AdditionalService;
import domain.Booking;
import domain.ConfigurationSystem;
import domain.Customer;

@Transactional
@Service
public class BookingService {

	// Managed Repository 
	// ====================================================================================

	@Autowired
	private BookingRepository			bookingRepository;

	//Services
	// ======================================================================

	@Autowired
	private Validator					validator;

	@Autowired
	private CustomerService				customerService;

	@Autowired
	private ConfigurationSystemService	configurationSystemService;

	@Autowired
	private MailService					mailService;


	// Constructor methods
	// ====================================================================================

	public BookingService() {
		super();
	}

	// Simple CRUDS methods 
	// ====================================================================================

	public Booking create(AdditionalService service) {
		Customer customer;
		Booking booking;

		Assert.notNull(service);
		customer = this.customerService.findByPrincipal();
		Assert.notNull(customer);

		booking = new Booking();
		booking.setCustomer(customer);
		booking.setService(service);
		booking.setWithPenalty(false);
		booking.setStatus("CONFIRMED");
		booking.setMoment(new Date(System.currentTimeMillis() + 6));

		return booking;
	}

	public Booking findOne(int id) {
		Booking booking;

		Assert.notNull(id);
		booking = this.bookingRepository.findOne(id);
		Assert.notNull(booking);

		return booking;
	}

	private Booking save(Booking booking) {
		Booking bookingSaved;

		Assert.notNull(booking);
		Assert.notNull(this.customerService.findByPrincipal());
		Assert.isTrue(booking.getCustomer().equals(this.customerService.findByPrincipal()));

		bookingSaved = this.bookingRepository.save(booking);
		Assert.notNull(bookingSaved);

		return bookingSaved;
	}

	public Booking book(Booking booking) throws ParseException {
		Booking bookingSaved;
		Date startMoment;
		Date endMoment;
		DateFormat timeFormat;

		timeFormat = new SimpleDateFormat("HH:mm:ss");
		startMoment = timeFormat.parse(booking.getMoment().getHours() + ":" + booking.getMoment().getMinutes() + ":01");
		endMoment = new Date((startMoment.getTime() - 1000) + (booking.getService().getDuration() * 60 * 1000));

		Assert.isTrue(booking.getService().getOpeningTime().before(startMoment) && booking.getService().getClosingTime().after(endMoment));
		Assert.isTrue(this.capacityAvailable(booking));
		bookingSaved = this.save(booking);
		Assert.notNull(bookingSaved);

		this.sendBookingConfirmation(bookingSaved);

		return bookingSaved;
	}

	private boolean capacityAvailable(Booking booking) {
		boolean available;
		Collection<Booking> bookings;
		Date startMoment;
		Date endMoment;

		startMoment = new Date(booking.getMoment().getTime() - (booking.getService().getDuration() * 60 * 1000));
		endMoment = new Date(booking.getMoment().getTime() + (booking.getService().getDuration() * 60 * 1000));

		bookings = this.bookingRepository.findAllConfirmedBetweenMomentsByService(startMoment, endMoment, booking.getService().getId());
		Assert.notNull(bookings);
		available = bookings.size() < booking.getService().getMaximumCapacity();

		return available;
	}

	public Booking cancel(int bookingId) {
		Booking booking;
		Booking bookingSaved;
		Date now;
		Long diff;
		String[] cancelTimeLimitArr;
		Long cancelTimeLimit;
		ConfigurationSystem configurationSystem;

		Assert.notNull(bookingId);
		booking = this.findOne(bookingId);
		Assert.notNull(this.customerService.findByPrincipal());
		Assert.isTrue(booking.getCustomer().equals(this.customerService.findByPrincipal()));
		now = new Date(System.currentTimeMillis());
		diff = booking.getMoment().getTime() - now.getTime();

		configurationSystem = this.configurationSystemService.findTheOne();
		cancelTimeLimitArr = configurationSystem.getCancelTimeLimit().split(":");
		cancelTimeLimit = ((Long.valueOf(cancelTimeLimitArr[0]) * (60 * 60 * 1000)) + (Long.valueOf(cancelTimeLimitArr[1]) * (60 * 1000)) + (Long.valueOf(cancelTimeLimitArr[2]) * (1000)));

		booking.setStatus("CANCELED");
		booking.setWithPenalty((diff < cancelTimeLimit) ? true : false);
		bookingSaved = this.save(booking);

		this.sendCancelBookingConfirmation(bookingSaved);

		return bookingSaved;
	}
	public Collection<Booking> findAllConfirmedByService(AdditionalService service) {
		Collection<Booking> bookings;

		bookings = this.bookingRepository.findAllConfirmedByService(service.getId());
		Assert.notNull(bookings);

		return bookings;
	}

	public Collection<Object[]> ratioCanceledBookingsTotalBookings() {
		return this.bookingRepository.ratioCanceledBookingsTotalBookings();
	}

	public Collection<Object[]> ratioCanceledBookingsWithPenaltyCanceledBookings() {
		return this.bookingRepository.ratioCanceledBookingsWithPenaltyCanceledBookings();
	}

	// Others bussines methods
	// ====================================================================================

	public Booking reconstruct(Booking booking, BindingResult binding) {
		Booking bookingReconstructed;

		if (booking.getId() == 0)
			bookingReconstructed = this.create(booking.getService());
		else
			bookingReconstructed = this.findOne(booking.getId());

		bookingReconstructed.setMoment(booking.getMoment());

		this.validator.validate(bookingReconstructed, binding);
		Assert.isTrue(!binding.hasErrors());

		return bookingReconstructed;
	}

	private void sendBookingConfirmation(Booking booking) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ConfigurationSystem config = this.configurationSystemService.findTheOne();

		String to = booking.getCustomer().getEmail();
		String subject = "Booking confirmation / Confirmación de reserva";
		String text;

		text = "Dear customer: \n\r";
		text += "We send you the confirmation of the booking you have made. \r\n";
		text += "Service: " + booking.getService().getName() + "\r\n";
		text += "Moment: " + format.format(booking.getMoment()) + "\r\n";
		text += "Remember: If you cancel a reservation with a difference less than " + config.getCancelTimeLimit() + " hours with the time of the reservation will have a penalty of " + config.getPenaltyAmount() + " euros \n\r";
		text += "Regards,\r\n";
		text += "the AcmeFit team.";

		text += "\r\n \r\n";

		text += "Estimado cliente: \n\r";
		text += "Le enviamos la confirmación de la reserva que ha realizado. \r\n";
		text += "Servicio: " + booking.getService().getName() + "\r\n";
		text += "Momento: " + format.format(booking.getMoment()) + "\r\n";
		text += "Recuerde: Si se cancela una reserva con una diferencia menor de " + config.getCancelTimeLimit() + " horas con el momento de la reserva tendra una penalización de " + config.getPenaltyAmount() + " euros \n\r";
		text += "Un saludo,\r\n";
		text += "el equipo de AcmeFit.";

		this.mailService.send(to, subject, text);
	}

	private void sendCancelBookingConfirmation(Booking booking) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ConfigurationSystem config = this.configurationSystemService.findTheOne();
		String to = booking.getCustomer().getEmail();
		String subject = "Booking cancellation / Cancelación de reserva";
		String text;

		text = "Dear customer: \n\r";
		text += "We send you the confirmation of the cancellation of the booking. \r\n";
		text += "Service: " + booking.getService().getName() + "\r\n";
		text += "Moment: " + format.format(booking.getMoment()) + "\r\n";
		text += "With penalty: " + (booking.getWithPenalty() ? "Yes" : "No") + "\r\n";
		if (booking.getWithPenalty())
			text += "Remember: This cancellation has a penalty of " + config.getPenaltyAmount() + " euros \n\r";
		text += "Regards,\r\n";
		text += "the AcmeFit team.";

		text += "\r\n \r\n";

		text += "Estimado cliente: \n\r";
		text += "Le enviamos la confirmación de la cancelación de la reserva. \r\n";
		text += "Servicio: " + booking.getService().getName() + "\r\n";
		text += "Momento: " + format.format(booking.getMoment()) + "\r\n";
		text += "Con penalización: " + (booking.getWithPenalty() ? "Sí" : "No") + "\r\n";
		if (booking.getWithPenalty())
			text += "Recuerde: esta cancelacion tiene una penalizacion de " + config.getPenaltyAmount() + " euros \n\r";
		text += "Un saludo,\r\n";
		text += "el equipo de AcmeFit.";

		this.mailService.send(to, subject, text);
	}
}
