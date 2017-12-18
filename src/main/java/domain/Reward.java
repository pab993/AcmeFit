
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class Reward {

	//Attributes
	// =======================================================

	private Integer	points;
	private String	objetive;
	private Date	archiveData;


	@NotNull
	public Integer getPoints() {
		return this.points;
	}
	public void setPoints(final Integer points) {
		this.points = points;
	}

	@NotBlank
	@SafeHtml
	public String getObjetive() {
		return this.objetive;
	}
	public void setObjetive(final String objetive) {
		this.objetive = objetive;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getArchiveData() {
		return this.archiveData;
	}
	public void setArchiveData(final Date archiveData) {
		this.archiveData = archiveData;
	}

}
