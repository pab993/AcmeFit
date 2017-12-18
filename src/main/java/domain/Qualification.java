
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Qualification extends DomainEntity {

	//Relationship
	//==========================================================

	private Competition	competition;
	private Customer	customer;


	@ManyToOne(optional = false)
	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(final Competition competition) {
		this.competition = competition;
	}

	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

}
