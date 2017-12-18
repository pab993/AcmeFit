
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.LineInvoice;

@Component
@Transactional
public class LineInvoiceToStringConverter implements Converter<LineInvoice, String> {

	@Override
	public String convert(LineInvoice invoice) {
		String result;
		if (invoice == null)
			result = null;
		else
			result = String.valueOf(invoice.getId());
		return result;
	}

}
