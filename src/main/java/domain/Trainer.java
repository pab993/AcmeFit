
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Trainer extends Employee {

	// Relationships
	// ===================================

	Collection<PersonalTraining>	personalTrainings;
	Collection<Activity>			activities;


	@OneToMany(mappedBy = "trainer")
	public Collection<PersonalTraining> getPersonalTrainings() {
		return this.personalTrainings;
	}

	public void setPersonalTrainings(final Collection<PersonalTraining> personalTrainings) {
		this.personalTrainings = personalTrainings;
	}

	@OneToMany(mappedBy = "trainer")
	public Collection<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(final Collection<Activity> activities) {
		this.activities = activities;
	}

}
