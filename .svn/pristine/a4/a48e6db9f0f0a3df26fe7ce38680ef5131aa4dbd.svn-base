
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.LineInvoiceRepository;
import domain.LineInvoice;

@Component
@Transactional
public class StringToLineInvoiceConverter implements Converter<String, LineInvoice> {

	@Autowired
	LineInvoiceRepository	lineInvoiceRepository;


	@Override
	public LineInvoice convert(String text) {
		LineInvoice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.lineInvoiceRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
