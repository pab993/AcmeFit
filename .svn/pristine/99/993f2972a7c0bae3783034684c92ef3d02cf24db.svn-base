
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Diet extends Comentable {

	//Attributes
	// =======================================================

	private Date	expirationMoment;
	private Double	estimateLostWeight;
	private String	hints;
	private Double	cost;
	private Reward	rewardsPoint;


	@Digits(fraction = 2, integer = 12)
	@Min(1)
	public Double getEstimateLostWeight() {
		return this.estimateLostWeight;
	}
	public void setEstimateLostWeight(final Double estimateLostWeight) {
		this.estimateLostWeight = estimateLostWeight;
	}

	@SafeHtml
	public String getHints() {
		return this.hints;
	}
	public void setHints(final String hints) {
		this.hints = hints;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getExpirationMoment() {
		return this.expirationMoment;
	}
	public void setExpirationMoment(final Date expirationMoment) {
		this.expirationMoment = expirationMoment;
	}

	@NotNull
	public Double getCost() {
		return this.cost;
	}
	public void setCost(final Double cost) {
		this.cost = cost;
	}

	public Reward getRewardsPoint() {
		return this.rewardsPoint;
	}
	public void setRewardsPoint(final Reward rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}


	//Relationships
	// =======================================================

	private Nutritionist			nutritionist;
	private Collection<Meal>		meals;
	private Collection<RequestDiet>	requestsDiets;


	@Valid
	@ManyToOne(optional = false)
	public Nutritionist getNutritionist() {
		return this.nutritionist;
	}
	public void setNutritionist(final Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Meal> getMeals() {
		return this.meals;
	}
	public void setMeals(final Collection<Meal> meals) {
		this.meals = meals;
	}

	@OneToMany(mappedBy = "diet")
	public Collection<RequestDiet> getRequestsDiets() {
		return this.requestsDiets;
	}
	public void setRequestsDiets(final Collection<RequestDiet> requestsDiets) {
		this.requestsDiets = requestsDiets;
	}

}
