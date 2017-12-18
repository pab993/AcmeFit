
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends Employee {

	//Relationships
	// =======================================================

	private Collection<Diet>	diets;


	@OneToMany(mappedBy = "nutritionist")
	public Collection<Diet> getDiets() {
		return this.diets;
	}

	public void setDiets(final Collection<Diet> diets) {
		this.diets = diets;
	}

}
