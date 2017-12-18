
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Diet;

@Component
@Transactional
public class DietToStringConverter implements Converter<Diet, String> {

	@Override
	public String convert(final Diet diet) {

		String result;
		if (diet == null)
			result = null;
		else
			result = String.valueOf(diet.getId());
		return result;
	}
}
