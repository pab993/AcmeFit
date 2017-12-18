
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RequestDietRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Diet;
import domain.Nutritionist;
import domain.RequestDiet;
import forms.RequestDietForm;

@Transactional
@Service
public class RequestDietService {

	//Repository
	//======================================================================

	@Autowired
	private RequestDietRepository	requestDietRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private DietService				dietService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private NutritionistService		nutritionistService;

	@Autowired
	private Validator				validator;


	//CRUD methods
	//=======================================================================

	public RequestDiet findOne(final int id) {
		RequestDiet requestDiet;

		requestDiet = this.requestDietRepository.findOne(id);
		Assert.notNull(requestDiet);

		return requestDiet;
	}

	public RequestDiet create(int dietId) {
		RequestDiet requestDiet = new RequestDiet();
		Assert.isInstanceOf(Customer.class, this.actorService.findByPrincipal());
		Diet diet = this.dietService.findOne(dietId);
		requestDiet.setCustomer(this.customerService.findByPrincipal());
		requestDiet.setStatus("PENDING");
		requestDiet.setDiet(diet);
		return requestDiet;
	}

	public RequestDiet save(RequestDiet requestDiet) {
		Assert.notNull(requestDiet);
		RequestDiet result;
		Assert.isTrue(this.checkIfCustomer() || this.checkIfNutritionist());
		result = this.requestDietRepository.save(requestDiet);

		return result;
	}
	public void delete(final RequestDiet requestDiet) {

		Assert.notNull(requestDiet);
		Assert.isTrue(this.checkIfCustomer() || this.checkIfNutritionist());

		this.requestDietRepository.delete(requestDiet);
	}

	public void deleteCustomer(final RequestDiet requestDiet) {

		Assert.notNull(requestDiet);
		Assert.isTrue(this.checkIfCustomer());

		this.requestDietRepository.delete(requestDiet);
	}

	public Collection<RequestDiet> findAll() {

		Collection<RequestDiet> requestDiets;

		requestDiets = this.requestDietRepository.findAll();
		Assert.notNull(requestDiets);

		return requestDiets;
	}

	//Other bussiness methods
	// ==================================================================

	public Collection<RequestDiet> findAllPendingByCustomer(Customer customer) {

		Collection<RequestDiet> requestDiets;

		requestDiets = this.requestDietRepository.findAllPendingByCustomer(customer.getId());

		return requestDiets;
	}

	public Collection<RequestDiet> findAllByCustomer(Customer customer) {

		Collection<RequestDiet> requestDiets;

		requestDiets = this.requestDietRepository.findAllByCustomer(customer.getId());

		return requestDiets;
	}

	public Collection<RequestDiet> findAllAcceptedByCustomer(Customer customer) {

		Collection<RequestDiet> requestDiets;

		requestDiets = this.requestDietRepository.findAllAcceptedByCustomer(customer.getId());

		return requestDiets;
	}

	public void cancelled(int requestDietId) {
		Customer customer;
		RequestDiet requestDiet;

		customer = this.customerService.findByPrincipal();
		requestDiet = this.findOne(requestDietId);

		Assert.isInstanceOf(Customer.class, customer);

		requestDiet.setStatus("DENIED");

		this.requestDietRepository.save(requestDiet);
	}

	public Boolean checkIfCustomer() {
		Boolean var = false;
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CUSTOMER");
		if (userAccount.getAuthorities().contains(au))
			var = true;
		return var;
	}

	public Boolean checkIfNutritionist() {
		Boolean var = false;
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("NUTRITIONIST");
		if (userAccount.getAuthorities().contains(au))
			var = true;
		return var;
	}

	public RequestDiet findByCustomerAndDiet(int customerId, int dietId) {
		RequestDiet requestDiet = this.requestDietRepository.findByCustomerAndDiet(customerId, dietId);
		//		Assert.notNull(requestDiet);
		return requestDiet;
	}

	public void applicationRequest(Diet diet) {

		Customer customer = this.customerService.findByPrincipal();
		RequestDiet requestDiet2 = this.requestDietRepository.findByCustomerAndDiet(customer.getId(), diet.getId());
		Assert.isNull(requestDiet2);

		RequestDiet requestDiet = this.create(diet.getId());
		this.save(requestDiet);
	}

	public RequestDietForm reconstructForm(RequestDiet requestDiet) {
		RequestDietForm res = new RequestDietForm();
		res.setStatus(requestDiet.getStatus());
		res.setRequestDietId(requestDiet.getId());
		res.setDiet(requestDiet.getDiet());

		return res;
	}

	public RequestDiet reconstruct(RequestDietForm requestDietForm, BindingResult binding) {
		RequestDiet result;

		result = new RequestDiet();
		Customer customer = this.customerService.findByPrincipal();
		result.setCustomer(customer);
		result.setDiet(requestDietForm.getDiet());
		result.setStatus("PENDING");

		this.validator.validate(result, binding);

		return result;

	}

	public RequestDiet reconstructByNutritionist(RequestDietForm requestDietForm, BindingResult binding) {
		RequestDiet result;

		result = this.requestDietRepository.findOne(requestDietForm.getRequestDietId());
		Nutritionist nutritionist = this.nutritionistService.findByPrincipal();
		Assert.isTrue(requestDietForm.getDiet().getNutritionist().equals(nutritionist));
		result.setStatus(requestDietForm.getStatus());

		this.validator.validate(result, binding);

		return result;

	}

	public Collection<RequestDiet> findRequestByNutritionist() {
		Collection<RequestDiet> requestsDiets;
		Nutritionist nutritionist = this.nutritionistService.findByPrincipal();

		requestsDiets = this.requestDietRepository.findRequestsByNutritionist(nutritionist.getId());
		Assert.notNull(requestsDiets);

		return requestsDiets;
	}
}
