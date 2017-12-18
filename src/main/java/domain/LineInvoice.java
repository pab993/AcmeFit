
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class LineInvoice extends DomainEntity {

	//attributes
	//================================================================

	private String	concept;
	private int		quantity;
	private Double	price;


	@NotBlank
	@SafeHtml
	public String getConcept() {
		return this.concept;
	}
	public void setConcept(final String concept) {
		this.concept = concept;
	}

	@NotNull
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	@Min(0)
	@Digits(fraction = 2, integer = 12)
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(final Double price) {
		this.price = price;
	}

}
