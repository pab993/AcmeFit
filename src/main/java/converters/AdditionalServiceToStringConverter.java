
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.AdditionalService;

@Component
@Transactional
public class AdditionalServiceToStringConverter implements Converter<AdditionalService, String> {

	@Override
	public String convert(final AdditionalService additionalService) {

		String result;
		if (additionalService == null)
			result = null;
		else
			result = String.valueOf(additionalService.getId());
		return result;
	}

}
