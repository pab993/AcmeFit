
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Trainer;

@Component
@Transactional
public class TrainerToStringConverter implements Converter<Trainer, String> {

	@Override
	public String convert(final Trainer trainer) {

		String result;
		if (trainer == null)
			result = null;
		else
			result = String.valueOf(trainer.getId());
		return result;
	}

}
