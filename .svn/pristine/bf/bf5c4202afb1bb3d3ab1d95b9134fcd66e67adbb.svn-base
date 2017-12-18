
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RegisterForRepository;
import domain.RegisterFor;

@Transactional
@Service
public class RegisterForService {

	public RegisterForService() {
		// TODO Auto-generated constructor stub
		super();
	}


	@Autowired
	private RegisterForRepository	registerForRepository;


	public RegisterFor save(RegisterFor registerFor) {
		Assert.notNull(registerFor);

		RegisterFor saved = registerForRepository.save(registerFor);

		return saved;
	}
}
