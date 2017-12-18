
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.RequestDietRepository;
import domain.RequestDiet;

@Component
@Transactional
public class StringToRequestTrainingConverter implements Converter<String, RequestDiet> {

	@Autowired
	RequestDietRepository	requestDietRepository;


	@Override
	public RequestDiet convert(final String text) {
		RequestDiet result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.requestDietRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
