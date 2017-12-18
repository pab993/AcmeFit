
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Attributes 
	// =================================================================

	private Integer	achievedReward;
	private Double	monthlyFee;


	@NotNull
	public Integer getAchievedReward() {
		return this.achievedReward;
	}
	public void setAchievedReward(final Integer achievedReward) {
		this.achievedReward = achievedReward;
	}

	@Min(0)
	@Digits(fraction = 2, integer = 12)
	public Double getMonthlyFee() {
		return this.monthlyFee;
	}
	public void setMonthlyFee(final Double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}


	//Relationships
	// =================================================================

	private Collection<Qualification>	qualifications;
	private Collection<RequestDiet>		requestDiets;
	private Collection<RequestTraining>	requestTrainings;
	private Collection<Invoice>			invoices;
	private Collection<RegisterFor>		registersFor;
	private Collection<Booking>			bookings;


	@OneToMany(mappedBy = "customer")
	public Collection<RequestDiet> getRequestDiets() {
		return this.requestDiets;
	}
	public void setRequestDiets(final Collection<RequestDiet> requestDiets) {
		this.requestDiets = requestDiets;
	}

	@OneToMany(mappedBy = "customer")
	public Collection<RequestTraining> getRequestTrainings() {
		return this.requestTrainings;
	}
	public void setRequestTrainings(final Collection<RequestTraining> requestTrainings) {
		this.requestTrainings = requestTrainings;
	}

	@OneToMany(mappedBy = "customer")
	public Collection<Invoice> getInvoices() {
		return this.invoices;
	}
	public void setInvoices(final Collection<Invoice> invoices) {
		this.invoices = invoices;
	}

	@OneToMany(mappedBy = "customer")
	public Collection<Qualification> getQualifications() {
		return this.qualifications;
	}
	public void setQualifications(final Collection<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	@OneToMany(mappedBy = "customer")
	public Collection<RegisterFor> getRegistersFor() {
		return this.registersFor;
	}
	public void setRegistersFor(final Collection<RegisterFor> registersFor) {
		this.registersFor = registersFor;
	}

	@OneToMany(mappedBy = "customer")
	public Collection<Booking> getBookings() {
		return this.bookings;
	}
	public void setBookings(final Collection<Booking> bookings) {
		this.bookings = bookings;
	}

}
