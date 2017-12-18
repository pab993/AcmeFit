
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QualificationRepository;
import domain.Qualification;

@Service
@Transactional
public class QualificationService {

	public QualificationService() {
		super();
	}


	@Autowired
	private QualificationRepository	qualificationRepository;


	public Qualification save(Qualification qualification) {
		Assert.notNull(qualification);

		Qualification saved = qualificationRepository.save(qualification);

		return saved;
	}
}
