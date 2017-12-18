
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Activity extends Exercise {

	//Attributes 
	// =================================================================

	private int	currentAttendance;
	private int	maximumAttendance;


	@NotNull
	@Min(0)
	public int getCurrentAttendance() {
		return this.currentAttendance;
	}
	public void setCurrentAttendance(final int currentAttendance) {
		this.currentAttendance = currentAttendance;
	}

	@NotNull
	@Min(0)
	public int getMaximumAttendance() {
		return this.maximumAttendance;
	}
	public void setMaximumAttendance(final int maximumAttendance) {
		this.maximumAttendance = maximumAttendance;
	}


	//Relationships
	// ================================================================

	private Collection<RegisterFor>	registersFor;
	private Trainer					trainer;


	@OneToMany(mappedBy = "activity")
	public Collection<RegisterFor> getRegistersFor() {
		return this.registersFor;
	}
	public void setRegistersFor(final Collection<RegisterFor> registersFor) {
		this.registersFor = registersFor;
	}

	@ManyToOne(optional = false)
	public Trainer getTrainer() {
		return this.trainer;
	}
	public void setTrainer(final Trainer trainer) {
		this.trainer = trainer;
	}
}
