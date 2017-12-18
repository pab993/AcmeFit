
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.RequestTrainingRepository;
import domain.RequestTraining;

@Component
@Transactional
public class StringToRequestDietConverter implements Converter<String, RequestTraining> {

	@Autowired
	RequestTrainingRepository	requestTrainingRepository;


	@Override
	public RequestTraining convert(final String text) {
		RequestTraining result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.requestTrainingRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
