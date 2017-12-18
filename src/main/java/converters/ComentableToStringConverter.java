
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Comentable;

@Component
@Transactional
public class ComentableToStringConverter implements Converter<Comentable, String>{

	@Override
	public String convert(Comentable comentable) {
		
		String result;
		if(comentable == null){
			result = null;
		}else{
			result = String.valueOf(comentable.getId());
		}
		return result;
	}
}
