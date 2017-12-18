
package forms;

import java.util.Collection;

import domain.Customer;
import domain.Diet;
import domain.Invoice;
import domain.PersonalTraining;

public class InvoiceForm {

	private Invoice							invoice;
	private Double							montlyPayment;
	private Double							discount;
	private Collection<Diet>				diets;
	private double							dietsPrice;
	private Collection<PersonalTraining>	personalTrainings;
	private Double							personalTrainingsPrice;

	private int								numberDiets;
	private int								numberPersonalTraingin;

	private Customer						customer;


	public int getNumberDiets() {
		return numberDiets;
	}

	public void setNumberDiets(int numberDiets) {
		this.numberDiets = numberDiets;
	}

	public int getNumberPersonalTraingin() {
		return numberPersonalTraingin;
	}

	public void setNumberPersonalTraingin(int numberPersonalTraingin) {
		this.numberPersonalTraingin = numberPersonalTraingin;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Double getMontlyPayment() {
		return montlyPayment;
	}

	public void setMontlyPayment(Double montlyPayment) {
		this.montlyPayment = montlyPayment;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Collection<Diet> getDiets() {
		return diets;
	}

	public void setDiets(Collection<Diet> diets) {
		this.diets = diets;
	}

	public double getDietsPrice() {
		return dietsPrice;
	}

	public void setDietsPrice(double dietsPrice) {
		this.dietsPrice = dietsPrice;
	}

	public Collection<PersonalTraining> getPersonalTrainings() {
		return personalTrainings;
	}

	public void setPersonalTrainings(Collection<PersonalTraining> personalTrainings) {
		this.personalTrainings = personalTrainings;
	}

	public Double getPersonalTrainingsPrice() {
		return personalTrainingsPrice;
	}

	public void setPersonalTrainingsPrice(Double personalTrainingsPrice) {
		this.personalTrainingsPrice = personalTrainingsPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
