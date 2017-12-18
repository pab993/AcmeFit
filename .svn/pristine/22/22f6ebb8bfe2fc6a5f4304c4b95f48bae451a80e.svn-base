
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class MailService {

	@Autowired
	private JavaMailSender	mailSender;


	// Constructor methods
	// ====================================================================================

	public MailService() {
		super();
	}

	// Send method. 
	// Encargado de enviar los emails. Recibe el destinatario,  el asunto y el cuerpo del email.
	// ====================================================================================
	public void send(String to, String subject, String text) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("acmefitnessinc@gmail.com"); //Añade el remitente del email
		mail.setTo(to); //Añade el destinaratio del email
		mail.setSubject(subject); //Añade el asunto del email
		mail.setText(text); //Añade el texto (cuerpo) del email

		this.mailSender.send(mail); //Envia el email al destinatario indicado
	}
}
