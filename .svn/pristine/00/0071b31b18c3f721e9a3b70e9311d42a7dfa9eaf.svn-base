
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity {

	//Attributes
	//=========================================================

	private Double	totalPrice;
	private Date	invoiceDate;
	private boolean	paid;


	@Min(0)
	@Digits(fraction = 2, integer = 12)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(final Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(final Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@NotNull
	public boolean getPaid() {
		return this.paid;
	}

	public void setPaid(final boolean paid) {
		this.paid = paid;
	}


	//Relationships
	//=========================================================
	private Collection<LineInvoice>	lineInvoices;
	private Customer				customer;


	@OneToMany(cascade = CascadeType.ALL)
	public Collection<LineInvoice> getLineInvoices() {
		return this.lineInvoices;
	}

	public void setLineInvoices(final Collection<LineInvoice> lineInvoices) {
		this.lineInvoices = lineInvoices;
	}

	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

}
