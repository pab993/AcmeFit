
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Comentable extends DomainEntity {

	//Attributes
	// ====================================================================================

	// Relationships
	// ====================================================================================

	private Collection<Assessment>	assessments;


	@OneToMany(mappedBy = "comentable")
	public Collection<Assessment> getAssessments() {
		return this.assessments;
	}

	public void setAssessments(final Collection<Assessment> assessments) {
		this.assessments = assessments;
	}
}
