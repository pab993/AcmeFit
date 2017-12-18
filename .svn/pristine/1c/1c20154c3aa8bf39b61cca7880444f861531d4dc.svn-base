
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EmployeeRepository;
import security.LoginService;
import security.UserAccount;
import domain.Employee;
import domain.Trainer;

@Transactional
@Service
public class EmployeeService {

	// Managed Repository 
	// ====================================================================================

	@Autowired
	private EmployeeRepository	employeeRepository;


	// Simple CRUDS methods 
	// ====================================================================================

	public Employee findOne(final int employeeId) {
		// TODO Auto-generated method stub
		Employee employee;
		employee = this.employeeRepository.findOne(employeeId);
		Assert.notNull(employee);
		return employee;
	}

	// Others bussines methods
	// ====================================================================================

	public Employee findByPrincipal() {
		Employee result;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			result = this.employeeRepository.findByUserAccountId(userAccount.getId());

		} catch (final Throwable exc) {
			result = null;

		}
		return result;
	}

	public String idEmployeeGenerator(final Employee employee) {
		String result = "";
		final String pattern = "0123456789";

		if (employee.getClass().equals(Trainer.class))
			result = "TRA-";
		else
			result = "NUT-";

		for (int i = 0; i <= 4; i++)
			result += pattern.charAt((int) (Math.random() * 10));

		return result;
	}
}
