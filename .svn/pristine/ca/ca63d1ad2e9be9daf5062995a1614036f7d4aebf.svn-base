
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class RequestTraining extends DomainEntity {

	//Attributes
	// ===================================================

	private String	status;


	@SafeHtml
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships
	// ===================================================

	private Customer			customer;
	private PersonalTraining	personalTraining;


	@Valid
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Valid
	@ManyToOne(optional = false)
	public PersonalTraining getPersonalTraining() {
		return this.personalTraining;
	}

	public void setPersonalTraining(final PersonalTraining personalTraining) {
		this.personalTraining = personalTraining;
	}
}
