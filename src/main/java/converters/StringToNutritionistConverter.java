
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.NutritionistRepository;
import domain.Nutritionist;

@Component
@Transactional
public class StringToNutritionistConverter implements Converter<String, Nutritionist> {

	@Autowired
	NutritionistRepository	nutritionistRepository;


	@Override
	public Nutritionist convert(final String text) {
		Nutritionist result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.nutritionistRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
