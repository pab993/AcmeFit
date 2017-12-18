
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.RequestTraining;

@Component
@Transactional
public class RequestTrainingToStringConverter implements Converter<RequestTraining, String> {

	@Override
	public String convert(final RequestTraining requestTraining) {

		String result;
		if (requestTraining == null)
			result = null;
		else
			result = String.valueOf(requestTraining.getId());
		return result;
	}

}
