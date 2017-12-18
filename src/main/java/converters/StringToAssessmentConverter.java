
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.AssessmentRepository;
import domain.Assessment;

@Component
@Transactional
public class StringToAssessmentConverter implements Converter<String, Assessment> {

	@Autowired
	AssessmentRepository	assessmentRepository;


	@Override
	public Assessment convert(final String text) {
		Assessment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.assessmentRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
