
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.PersonalTrainingRepository;
import domain.PersonalTraining;

@Component
@Transactional
public class StringToPersonalTrainingConverter implements Converter<String, PersonalTraining> {

	@Autowired
	PersonalTrainingRepository	personalTrainingRepository;


	@Override
	public PersonalTraining convert(final String text) {
		PersonalTraining result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.personalTrainingRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
