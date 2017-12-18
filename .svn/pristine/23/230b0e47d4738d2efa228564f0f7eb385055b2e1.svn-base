
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.AdditionalServiceRepository;
import domain.AdditionalService;

@Component
@Transactional
public class StringToAdditionalServiceConverter implements Converter<String, AdditionalService> {

	@Autowired
	AdditionalServiceRepository	additionalServiceRepository;


	@Override
	public AdditionalService convert(final String text) {
		AdditionalService result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.additionalServiceRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
