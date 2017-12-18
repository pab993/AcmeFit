
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.PersonalTraining;

@Component
@Transactional
public class PersonalTrainingToStringConverter implements Converter<PersonalTraining, String> {

	@Override
	public String convert(final PersonalTraining personalTraining) {

		String result;
		if (personalTraining == null)
			result = null;
		else
			result = String.valueOf(personalTraining.getId());
		return result;
	}

}
