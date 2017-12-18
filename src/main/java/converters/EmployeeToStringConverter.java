
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Employee;

@Component
@Transactional
public class EmployeeToStringConverter implements Converter<Employee, String> {

	@Override
	public String convert(final Employee employee) {

		String result;
		if (employee == null)
			result = null;
		else
			result = String.valueOf(employee.getId());
		return result;
	}
}
