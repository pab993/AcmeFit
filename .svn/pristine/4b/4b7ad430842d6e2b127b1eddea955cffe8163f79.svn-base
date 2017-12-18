
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.LineInvoiceRepository;
import domain.LineInvoice;

@Service
@Transactional
public class LineInvoiceService {

	public LineInvoiceService() {
		super();
	}


	@Autowired
	private LineInvoiceRepository	lineInvoiceRepository;


	public LineInvoice create() {
		LineInvoice lineInvoice = new LineInvoice();
		lineInvoice.setQuantity(0);
		lineInvoice.setPrice(0.0);

		return lineInvoice;
	}
}
