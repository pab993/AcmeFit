
package services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CreditCardRepository;
import domain.Actor;
import domain.BrandNameCreditCard;
import domain.CreditCard;
import domain.Customer;
import domain.Nutritionist;
import domain.Trainer;
import forms.CustomerForm;
import forms.NutritionistForm;
import forms.TrainerForm;

@Transactional
@Service
public class CreditCardService {

	//Repository
	//======================================================================

	@Autowired
	private CreditCardRepository	creditCardRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService			actorService;


	//CRUD methods
	//=======================================================================

	public CreditCard findOne(int id) {
		CreditCard creditCard;

		creditCard = this.creditCardRepository.findOne(id);
		Assert.notNull(creditCard);

		return creditCard;
	}

	public CreditCard create() {

		CreditCard cc = new CreditCard();
		return cc;

	}

	public CreditCard save(CreditCard c) {
		Assert.notNull(c);
		Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(c.getActor().getId() == actor.getId());
		Assert.isTrue(this.checkValidity(c));
		//		delete(c);
		CreditCard creditCardRes = this.creditCardRepository.save(c);
		return creditCardRes;
	}

	public CreditCard save2(CreditCard c) {
		Assert.notNull(c);

		Assert.isTrue(this.checkValidity(c));
		//		delete(c);
		CreditCard creditCardRes = this.creditCardRepository.save(c);
		return creditCardRes;
	}

	public void delete(CreditCard c) {
		Assert.notNull(c);
		Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(c.getActor().getId() == actor.getId());
		Assert.isTrue(c.getId() != 0);
		Assert.isTrue(this.creditCardRepository.exists(c.getId()));
		this.creditCardRepository.delete(c);
	}

	//Other bussiness methods
	//=======================================================================

	public int[] stringToArray(String number) {
		char[] a = number.toCharArray();
		int[] n = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			int x = Character.getNumericValue(a[i]);
			n[i] = x;
		}
		return n;
	}
	public boolean verificacionLuhn(int[] digits) {
		int sum = 0;
		int length = digits.length;
		for (int i = 0; i < length; i++) {
			// sacar los digitos en orden inverso
			int digit = digits[length - i - 1];

			// cada segundo n�mero se multiplica por 2
			if (i % 2 == 1)
				digit = digit * 2;
			if (digit > 9)
				digit = digit - 9;
			sum = sum + digit;
		}
		return sum % 10 == 0;
	}

	public CreditCard findByActorId(int actorId) {
		CreditCard creditCard;
		Assert.notNull(actorId);

		creditCard = this.creditCardRepository.findByActorId(actorId);

		return creditCard;

	}

	public boolean checkValidity(CreditCard creditCard) {

		boolean res = false;
		int[] n = this.stringToArray(creditCard.getNumber());

		if (this.checkBrandName(creditCard) && this.verificacionLuhn(n) && this.checkExpirationDate(creditCard))
			res = true;
		return res;
	}

	public boolean checkBrandName(CreditCard creditCard) {

		boolean res = false;

		for (BrandNameCreditCard bn : BrandNameCreditCard.values())
			if (bn.name().equals(creditCard.getBrandName()))
				res = true;
		return res;
	}

	public boolean checkExpirationDate(CreditCard creditCard) {
		boolean res = true;

		long l = 10;
		Calendar actualCalendar = Calendar.getInstance();
		Date actual = new Date(System.currentTimeMillis() - l);
		actualCalendar.setTime(actual);

		if (creditCard.getExpirationYear() < actualCalendar.get(Calendar.YEAR))
			res = false;
		else if (creditCard.getExpirationYear() >= actualCalendar.get(Calendar.YEAR) && creditCard.getExpirationMonth() < actualCalendar.get(Calendar.MONTH))
			res = false;

		return res;
	}


	@Autowired
	Validator	validator;


	public CreditCard reconstruct(CustomerForm customerForm, Customer saved, BindingResult binding) {
		// TODO Auto-generated method stub

		CreditCard creditCard = this.create();

		creditCard.setHolderName(customerForm.getHolderName());
		creditCard.setBrandName(customerForm.getBrandName());
		creditCard.setExpirationMonth(customerForm.getExpirationMonth());
		creditCard.setExpirationYear(customerForm.getExpirationYear());
		creditCard.setNumber(customerForm.getNumber());
		creditCard.setCVV(customerForm.getCVV());

		creditCard.setActor(saved);

		validator.validate(customerForm, binding);

		return creditCard;
	}

	public CreditCard reconstruct2(TrainerForm trainerForm, Trainer saved, BindingResult binding) {
		// TODO Auto-generated method stub

		CreditCard creditCard = this.create();

		creditCard.setHolderName(trainerForm.getHolderName());
		creditCard.setBrandName(trainerForm.getBrandName());
		creditCard.setExpirationMonth(trainerForm.getExpirationMonth());
		creditCard.setExpirationYear(trainerForm.getExpirationYear());
		creditCard.setNumber(trainerForm.getNumber());
		creditCard.setCVV(trainerForm.getCVV());

		creditCard.setActor(saved);

		validator.validate(trainerForm, binding);

		return creditCard;
	}

	public CreditCard reconstruct3(NutritionistForm nutritionistForm, Nutritionist saved, BindingResult binding) {
		// TODO Auto-generated method stub

		CreditCard creditCard = this.create();

		creditCard.setHolderName(nutritionistForm.getHolderName());
		creditCard.setBrandName(nutritionistForm.getBrandName());
		creditCard.setExpirationMonth(nutritionistForm.getExpirationMonth());
		creditCard.setExpirationYear(nutritionistForm.getExpirationYear());
		creditCard.setNumber(nutritionistForm.getNumber());
		creditCard.setCVV(nutritionistForm.getCVV());

		creditCard.setActor(saved);

		validator.validate(nutritionistForm, binding);

		return creditCard;
	}

}
