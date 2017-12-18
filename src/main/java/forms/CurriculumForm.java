
package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

public class CurriculumForm {

	//Attributes
	// =======================================================

	private Integer	curriculumId;
	private String	photograph;
	private String	academicFormation;
	private String	experience;


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

	@SafeHtml
	@NotBlank
	public String getExperience() {
		return this.experience;
	}
	public void setExperience(final String experience) {
		this.experience = experience;
	}

	@NotNull
	public int getCurriculumId() {
		return this.curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}

}
