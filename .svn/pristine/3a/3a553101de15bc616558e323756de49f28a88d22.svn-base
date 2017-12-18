
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Meal extends DomainEntity {

	//Attributes
	// =======================================================

	private String	name;
	private int		kcal;
	private String	nutritionalValue;
	private Date	schedule;


	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@Min(0)
	@Max(500)
	@NotNull
	public int getKcal() {
		return this.kcal;
	}
	public void setKcal(final int kcal) {
		this.kcal = kcal;
	}

	@NotBlank
	@SafeHtml
	public String getNutritionalValue() {
		return this.nutritionalValue;
	}
	public void setNutritionalValue(final String nutritionalValue) {
		this.nutritionalValue = nutritionalValue;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSchedule() {
		return this.schedule;
	}
	public void setSchedule(final Date schedule) {
		this.schedule = schedule;
	}


	//Relationships
	// =======================================================

	private Diet					diet;
	private Collection<Ingredient>	ingredients;


	@Valid
	@ManyToOne(optional = false)
	public Diet getDiet() {
		return this.diet;
	}
	public void setDiet(final Diet diet) {
		this.diet = diet;
	}

	@OneToMany
	public Collection<Ingredient> getIngredients() {
		return this.ingredients;
	}
	public void setIngredients(final Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
