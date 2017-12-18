
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Assessment;

@Component
@Transactional
public class AssessmentToStringConverter implements Converter<Assessment, String> {

	@Override
	public String convert(final Assessment assessment) {

		String result;
		if (assessment == null)
			result = null;
		else
			result = String.valueOf(assessment.getId());
		return result;
	}

}
