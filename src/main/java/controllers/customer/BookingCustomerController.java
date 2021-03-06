
package controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.AdditionalServiceService;
import services.BookingService;
import services.ConfigurationSystemService;
import services.CreditCardService;
import services.CustomerService;
import controllers.AbstractController;
import domain.AdditionalService;
import domain.Booking;
import domain.CreditCard;
import domain.Customer;

@Controller
@RequestMapping("/booking/customer")
public class BookingCustomerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public BookingCustomerController() {
		super();
	}


	// Services -----------------------------------------------------------

	@Autowired
	private BookingService				bookingService;

	@Autowired
	private CustomerService				customerService;

	@Autowired
	private AdditionalServiceService	additionalServiceService;

	@Autowired
	private ConfigurationSystemService	configurationSystemService;

	@Autowired
	private CreditCardService			creditCardService;


	@RequestMapping(value = "/myBookings", method = RequestMethod.GET)
	public ModelAndView myBookings() {
		ModelAndView view;
		Customer customer;

		customer = this.customerService.findByPrincipal();

		if (customer == null)
			view = new ModelAndView("redirect:/");
		else {
			view = new ModelAndView("booking/list");
			view.addObject("bookings", customer.getBookings());
			view.addObject("requestURI", "booking/customer/myBookings.do");
			view.addObject("configurationSystem", this.configurationSystemService.findTheOne());
		}

		return view;
	}

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public ModelAndView book(@RequestParam int serviceId, RedirectAttributes redirectAttrs) {
		ModelAndView view;
		AdditionalService service;
		Booking booking;
		Customer customer;
		CreditCard creditCard;

		customer = this.customerService.findByPrincipal();
		if (customer == null)
			view = new ModelAndView("redirect:/");
		else {
			creditCard = this.creditCardService.findByActorId(customer.getId());

			//if ((creditCard != null) && (this.creditCardService.checkValidity(creditCard))) {
			service = this.additionalServiceService.findOne(serviceId);
			booking = this.bookingService.create(service);
			view = this.createModelAndView(booking);
			//} else {
			//view = new ModelAndView("redirect:/service/list.do");
			//redirectAttrs.addFlashAttribute("message", "booking.creditCard.noValid");
			//}

		}

		return view;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ModelAndView book(Booking booking, BindingResult binding, RedirectAttributes redirectAttrs) {
		ModelAndView view;
		Customer customer;
		CreditCard creditCard;

		customer = this.customerService.findByPrincipal();
		if (customer == null)
			view = new ModelAndView("redirect:/");
		else {
			creditCard = this.creditCardService.findByActorId(customer.getId());

			if ((creditCard != null) && (this.creditCardService.checkValidity(creditCard)))
				try {
					booking = this.bookingService.reconstruct(booking, binding);
					if (binding.hasErrors())
						view = this.createModelAndView(booking);
					else {
						booking = this.bookingService.book(booking);
						view = new ModelAndView("redirect:myBookings.do");
						redirectAttrs.addFlashAttribute("message", "booking.commit.ok");
					}
				} catch (Throwable oops) {
					if (binding.hasErrors())
						view = this.createModelAndView(booking);
					else
						view = this.createModelAndView(booking, "booking.commit.error");
				}
			else {
				view = new ModelAndView("redirect:service/list.do");
				redirectAttrs.addFlashAttribute("message", "booking.creditCard.noValid");
			}

		}

		return view;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancelBooking(@RequestParam int bookingId, RedirectAttributes redirectAttrs) {
		ModelAndView view;
		Customer customer;
		CreditCard creditCard;

		customer = this.customerService.findByPrincipal();
		if (customer == null)
			view = new ModelAndView("redirect:/");
		else {
			creditCard = this.creditCardService.findByActorId(customer.getId());

			if ((creditCard != null) && (this.creditCardService.checkValidity(creditCard)))
				try {
					this.bookingService.cancel(bookingId);
					view = new ModelAndView("redirect:myBookings.do");
					redirectAttrs.addFlashAttribute("message", "booking.commit.ok");
				} catch (Throwable oops) {
					view = new ModelAndView("redirect:myBookings.do");
					redirectAttrs.addFlashAttribute("message", "booking.commit.error");
				}
			else {
				view = new ModelAndView("redirect:myBookings.do");
				redirectAttrs.addFlashAttribute("message", "booking.creditCard.noValid");
			}

		}

		return view;
	}

	private ModelAndView createModelAndView(Booking booking) {
		return this.createModelAndView(booking, null);
	}

	private ModelAndView createModelAndView(Booking booking, String message) {
		ModelAndView view;

		view = new ModelAndView("booking/edit");
		view.addObject("booking", booking);
		view.addObject("message", message);

		return view;
	}

}
