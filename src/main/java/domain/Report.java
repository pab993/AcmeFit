
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//Attributes
	// =======================================================

	private int		timeSpent;
	private int		kcalSpent;
	private int		mediumPulse;
	private String	exercisedMuscles;
	private int		score;
	private String	hints;


	@Min(0)
	@Max(120)
	@NotNull
	public int getTimeSpent() {
		return this.timeSpent;
	}
	public void setTimeSpent(final int timeSpent) {
		this.timeSpent = timeSpent;
	}

	@Min(0)
	@Max(1000)
	@NotNull
	public int getKcalSpent() {
		return this.kcalSpent;
	}
	public void setKcalSpent(final int kcalSpent) {
		this.kcalSpent = kcalSpent;
	}

	@Min(0)
	@Max(180)
	@NotNull
	public int getMediumPulse() {
		return this.mediumPulse;
	}
	public void setMediumPulse(final int mediumPulse) {
		this.mediumPulse = mediumPulse;
	}

	@SafeHtml
	@NotBlank
	public String getExercisedMuscles() {
		return this.exercisedMuscles;
	}
	public void setExercisedMuscles(final String exercisedMuscles) {
		this.exercisedMuscles = exercisedMuscles;
	}

	@Min(0)
	@Max(10)
	@NotNull
	public int getScore() {
		return this.score;
	}
	public void setScore(final int score) {
		this.score = score;
	}

	@SafeHtml
	@NotBlank
	public String getHints() {
		return this.hints;
	}
	public void setHints(final String hints) {
		this.hints = hints;
	}

}
