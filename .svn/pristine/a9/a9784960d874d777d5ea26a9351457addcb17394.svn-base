
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import domain.Diet;

public class RequestDietForm {

	//Attributes
	// =======================================================

	private int		requestDietId;
	private String	status;
	private Diet	diet;


	@NotNull
	public int getRequestDietId() {
		return this.requestDietId;
	}

	public void setRequestDietId(int requestDietId) {
		this.requestDietId = requestDietId;
	}

	@Valid
	@NotNull
	public Diet getDiet() {
		return this.diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
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
