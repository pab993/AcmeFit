
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Diet;
import domain.Nutritionist;
import forms.NutritionistForm;

@Transactional
@Service
public class NutritionistService {

	// Managed Repository 
	// ====================================================================================

	@Autowired
	private NutritionistRepository	nutritionistRepository;

	// Supporting Services
	// ====================================================================================

	@Autowired
	private EmployeeService			employeeService;


	// Constructor methods
	// ====================================================================================

	public NutritionistService() {
		super();
	}

	// Simple CRUDS methods 
	// ====================================================================================

	public Nutritionist findOne(final int id) {

		Assert.isTrue(this.nutritionistRepository.exists(id));

		final Nutritionist nutritionist = this.nutritionistRepository.findOne(id);
		Assert.notNull(nutritionist);

		return nutritionist;
	}

	public Collection<Nutritionist> findAll() {

		Collection<Nutritionist> nutritionists;

		nutritionists = this.nutritionistRepository.findAll();
		Assert.notEmpty(nutritionists);

		return nutritionists;
	}

	public Nutritionist create() {

		final Nutritionist nutritionist = new Nutritionist();
		Collection<Diet> diets;
		final String idEmployee = this.employeeService.idEmployeeGenerator(nutritionist);

		nutritionist.setUserAccount(new UserAccount());
		nutritionist.getUserAccount().setAuthorities(new ArrayList<Authority>());

		final Authority authority = new Authority();
		authority.setAuthority("NUTRITIONIST");
		diets = new HashSet<Diet>();

		nutritionist.getUserAccount().getAuthorities().add(authority);

		nutritionist.setDiets(diets);
		nutritionist.setIdEmployee(idEmployee);
		nutritionist.setFee(0.0);

		return nutritionist;
	}
	public Nutritionist save(final Nutritionist nutritionist) {
		Assert.notNull(nutritionist);

		final Nutritionist saved = this.nutritionistRepository.save(nutritionist);

		return saved;
	}

	public void delete(final Nutritionist nutritionist) {
		Assert.notNull(nutritionist, "Trainer null");
		Assert.isTrue(this.nutritionistRepository.exists(nutritionist.getId()), "didn't found");

		this.nutritionistRepository.delete(nutritionist);
	}

	// Others bussines methods
	// ====================================================================================

	public Nutritionist findByPrincipal() {
		Nutritionist result;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			result = this.nutritionistRepository.findByUserAccountId(userAccount.getId());

		} catch (final Throwable exc) {
			result = null;

		}
		return result;
	}


	// Reconstruct
	// ====================================================================================

	@Autowired
	Validator	validator;


	public Nutritionist reconstruct(final NutritionistForm nutritionistForm, final BindingResult binding) {
		// TODO Auto-generated method stub

		final Nutritionist nutritionist = this.create();

		nutritionist.setName(nutritionistForm.getName());
		nutritionist.setSurname(nutritionistForm.getSurname());
		nutritionist.setAddress(nutritionistForm.getAddress());
		nutritionist.setEmail(nutritionistForm.getEmail());
		nutritionist.setPhone(nutritionistForm.getPhone());
		nutritionist.getUserAccount().setUsername(nutritionistForm.getUsername());
		nutritionist.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(nutritionistForm.getPassword(), null));
		nutritionist.setPicture(nutritionistForm.getPicture());

		/*
		 * Match passwords?
		 */

		if (nutritionistForm.getPassword() != null && nutritionistForm.getRepeatPassword() != null)
			if (!nutritionistForm.getPassword().equals(nutritionistForm.getRepeatPassword()))
				nutritionistForm.setRepeatPassword(null);

		/*
		 * Si el cliente no quiere registrar un tarjeta, crearemos una
		 * tarjeta fantasma que no será almacenada
		 */
		if (!nutritionistForm.isCheck2()) {
			nutritionistForm.setHolderName("VISA");
			nutritionistForm.setBrandName("Usuario Fantasma");
			nutritionistForm.setCVV(451);
			nutritionistForm.setNumber("4556464860159704");
			nutritionistForm.setExpirationMonth(02);
		}

		this.validator.validate(nutritionistForm, binding);

		/*
		 * Eliminamos la tarjeta fantasma para mostrar el formulario correctamente
		 */
		if (!nutritionistForm.isCheck2()) {
			nutritionistForm.setHolderName(null);
			nutritionistForm.setBrandName(null);
			nutritionistForm.setCVV(0);
			nutritionistForm.setNumber(null);
			nutritionistForm.setExpirationMonth(0);
		}

		return nutritionist;
	}

	public Collection<Nutritionist> findOrderByPopularity() {
		Collection<Nutritionist> res = this.nutritionistRepository.findOrderByPopularity();
		Assert.notNull(res);
		return res;
	}
}
