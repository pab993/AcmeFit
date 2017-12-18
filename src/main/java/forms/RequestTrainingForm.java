
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import domain.PersonalTraining;

public class RequestTrainingForm {

	//Attributes
	// =======================================================

	private int					requestTrainingId;
	private String				status;
	private PersonalTraining	personalTraining;


	@NotNull
	public int getRequestTrainingId() {
		return this.requestTrainingId;
	}

	public void setRequestTrainingId(int requestTrainingId) {
		this.requestTrainingId = requestTrainingId;
	}

	@Valid
	@NotNull
	public PersonalTraining getPersonalTraining() {
		return this.personalTraining;
	}

	public void setPersonalTraining(PersonalTraining personalTraining) {
		this.personalTraining = personalTraining;
	}

	@SafeHtml
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
