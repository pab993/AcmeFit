
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity {

	//Attributes 
	// =================================================================

	private String	name;
	private Double	multiplicity;
	private String	unit;


	@SafeHtml
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Min(1)
	@Digits(integer = 12, fraction = 2)
	public Double getMultiplicity() {
		return this.multiplicity;
	}

	public void setMultiplicity(final Double multiplicity) {
		this.multiplicity = multiplicity;
	}

	@NotBlank
	@SafeHtml
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(final String unit) {
		this.unit = unit;
	}

}
