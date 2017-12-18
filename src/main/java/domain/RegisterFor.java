
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class RegisterFor extends DomainEntity {

	//Relationship
	//==========================================================

	private Activity	activity;
	private Customer	customer;


	@ManyToOne(optional = false)
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(final Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}
}
