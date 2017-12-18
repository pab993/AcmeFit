
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.RequestDiet;

@Component
@Transactional
public class RequestDietToStringConverter implements Converter<RequestDiet, String> {

	@Override
	public String convert(final RequestDiet requestDiet) {

		String result;
		if (requestDiet == null)
			result = null;
		else
			result = String.valueOf(requestDiet.getId());
		return result;
	}

}
