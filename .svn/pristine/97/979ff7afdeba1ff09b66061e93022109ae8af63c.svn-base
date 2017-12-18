
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Exercise extends Comentable {

	//Attributes
	// =======================================================

	private String	nameRoom;
	private String	nameActivity;
	private String	description;
	private Integer	duration;
	private String	intensity;
	private Date	schedule;
	private Reward	rewardsPoint;


	@SafeHtml
	@NotBlank
	public String getNameRoom() {
		return this.nameRoom;
	}
	public void setNameRoom(final String nameRoom) {
		this.nameRoom = nameRoom;
	}

	@SafeHtml
	@NotBlank
	public String getNameActivity() {
		return this.nameActivity;
	}
	public void setNameActivity(final String nameActivity) {
		this.nameActivity = nameActivity;
	}

	@SafeHtml
	@NotBlank
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@Min(1)
	public Integer getDuration() {
		return this.duration;
	}
	public void setDuration(final Integer duration) {
		this.duration = duration;
	}

	@SafeHtml
	@NotBlank
	public String getIntensity() {
		return this.intensity;
	}
	public void setIntensity(final String intensity) {
		this.intensity = intensity;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSchedule() {
		return this.schedule;
	}
	public void setSchedule(final Date schedule) {
		this.schedule = schedule;
	}

	public Reward getRewardsPoint() {
		return this.rewardsPoint;
	}
	public void setRewardsPoint(final Reward rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}

	//Relationships
	// ============================================================

}
