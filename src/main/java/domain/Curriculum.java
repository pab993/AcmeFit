
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	//Constructor
	// =======================================================

	//Attributes
	// =======================================================

	private String	photograph;
	private String	academicFormation;
	private String	experience;
	private Date	uploadTime;


	@SafeHtml
	@NotBlank
	@URL
	public String getPhotograph() {
		return this.photograph;
	}
	public void setPhotograph(final String photograph) {
		this.photograph = photograph;
	}

	@SafeHtml
	@NotBlank
	public String getAcademicFormation() {
		return this.academicFormation;
	}
	public void setAcademicFormation(final String academicFormation) {
		this.academicFormation = academicFormation;
	}

	@NotBlank
	@SafeHtml
	public String getExperience() {
		return this.experience;
	}
	public void setExperience(final String experience) {
		this.experience = experience;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getUploadTime() {
		return this.uploadTime;
	}
	public void setUploadTime(final Date uploadTime) {
		this.uploadTime = uploadTime;
	}


	//Relationships------------------------------

	private Employee	employee;


	@Valid
	@OneToOne(optional = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}
}
