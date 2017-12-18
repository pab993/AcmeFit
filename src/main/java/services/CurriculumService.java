
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CurriculumRepository;
import security.LoginService;
import security.UserAccount;
import domain.Curriculum;
import domain.Employee;
import forms.CurriculumForm;

@Transactional
@Service
public class CurriculumService {

	//Repository
	//======================================================================

	@Autowired
	private CurriculumRepository	curriculumRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private EmployeeService			employeeService;

	@Autowired
	private Validator				validator;


	//CRUD methods
	//=======================================================================

	public Curriculum findOne(final int id) {
		Curriculum curriculum;

		curriculum = this.curriculumRepository.findOne(id);
		Assert.notNull(curriculum);

		return curriculum;
	}

	public Curriculum create() {
		final Curriculum curriculum = new Curriculum();
		Assert.isInstanceOf(Employee.class, this.actorService.findByPrincipal());
		return curriculum;
	}

	public Curriculum save(Curriculum curriculum) {
		Assert.notNull(curriculum);
		Assert.isInstanceOf(Employee.class, this.actorService.findByPrincipal());
		final Employee employee = this.employeeService.findByPrincipal();
		//		curriculum.setEmployee(employee);
		Assert.isTrue(employee.equals(curriculum.getEmployee()));
		final Curriculum curriculumRes = this.curriculumRepository.save(curriculum);

		return curriculumRes;
	}

	public void delete(final Curriculum curriculum) {

		Assert.notNull(curriculum);
		this.checkPrincipal(curriculum);

		this.save(curriculum);

		this.curriculumRepository.delete(curriculum);
	}

	public Collection<Curriculum> findAll() {

		Collection<Curriculum> curriculums;

		curriculums = this.curriculumRepository.findAll();
		Assert.notNull(curriculums);

		return curriculums;
	}

	//Other bussiness methods
	// ==================================================================

	private void checkPrincipal(final Curriculum curriculum) {
		// comprueba qe el curriculum pertence al usuario
		final Employee employee = this.employeeService.findByPrincipal();
		Assert.isTrue(curriculum.getEmployee().equals(employee));
	}

	public Curriculum findByUserAccount() {
		Curriculum curriculum;
		final UserAccount userAccount = LoginService.getPrincipal();
		curriculum = this.curriculumRepository.findByUserAccount(userAccount.getId());

		return curriculum;
	}

	public Curriculum reconstruct(CurriculumForm curriculumForm, BindingResult binding) {
		Curriculum result;

		if (curriculumForm.getCurriculumId() == 0) {
			result = new Curriculum();
			Employee employee = this.employeeService.findByPrincipal();
			result.setAcademicFormation(curriculumForm.getAcademicFormation());
			result.setExperience(curriculumForm.getExperience());
			result.setPhotograph(curriculumForm.getPhotograph());
			result.setEmployee(employee);
		} else {
			result = this.curriculumRepository.findOne(curriculumForm.getCurriculumId());

			result.setAcademicFormation(curriculumForm.getAcademicFormation());
			result.setExperience(curriculumForm.getExperience());
			result.setPhotograph(curriculumForm.getPhotograph());

		}
		final long l = 10;
		final Date actual = new Date(System.currentTimeMillis() - l);
		final Calendar actualDate = Calendar.getInstance();
		actualDate.setTime(actual);

		result.setUploadTime(actualDate.getTime());

		this.validator.validate(result, binding);

		return result;
	}
	public CurriculumForm reconstructForm(Curriculum curriculum) {
		CurriculumForm res = new CurriculumForm();
		res.setCurriculumId(curriculum.getId());
		res.setAcademicFormation(curriculum.getAcademicFormation());
		res.setExperience(curriculum.getExperience());
		res.setPhotograph(curriculum.getPhotograph());
		return res;
	}

	public Curriculum findByEmployeeId(int employeeId) {
		Curriculum curriculum;

		curriculum = this.curriculumRepository.findByEmployeeId(employeeId);

		return curriculum;
	}
}
