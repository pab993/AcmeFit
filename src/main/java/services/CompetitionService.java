
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CompetitionRepository;
import domain.Administrator;
import domain.Assessment;
import domain.Competition;
import domain.Qualification;

@Transactional
@Service
public class CompetitionService {

	// Repositories
	// ====================================================================

	@Autowired
	private CompetitionRepository	competitionRepository;

	// Supporting services
	// ====================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private Validator				validator;


	// Simple CRUD methods
	// ====================================================================

	public Competition findOne(final int competitionId) {
		Assert.isTrue(competitionId != 0);
		Assert.isTrue(competitionRepository.exists(competitionId));
		Competition result;

		result = this.competitionRepository.findOne(competitionId);

		return result;
	}

	public Collection<Competition> findAll() {
		Collection<Competition> result;

		result = this.competitionRepository.findAll();

		return result;
	}

	public Competition create() {
		Assert.isInstanceOf(Administrator.class, this.actorService.findByPrincipal());

		Competition result;

		result = new Competition();
		result.setAssessments(new ArrayList<Assessment>());
		result.setQualifications(new ArrayList<Qualification>());

		return result;
	}
	public Competition save(final Competition competition) {
		Assert.notNull(competition);
		Competition result;

		Assert.isTrue(competition.getStartDate().after(new Date(System.currentTimeMillis() - 10)));
		Assert.isTrue(competition.getStartDate().before(competition.getClosingDate()));

		result = this.competitionRepository.save(competition);

		return result;
	}

	public Competition update(final Competition competition) {
		Assert.notNull(competition);
		Competition result;

		Assert.isTrue(competitionRepository.exists(competition.getId()));

		result = this.competitionRepository.save(competition);

		return result;
	}

	// Other business methods
	// ===================================================================

	public Competition reconstruct(Competition competition, BindingResult binding) {
		Competition competitionReconstructed;

		if (competition.getId() == 0)
			competitionReconstructed = this.create();
		else
			competitionReconstructed = this.findOne(competition.getId());

		competitionReconstructed.setDescription(competition.getDescription());
		competitionReconstructed.setStartDate(competition.getStartDate());
		competitionReconstructed.setClosingDate(competition.getClosingDate());
		competitionReconstructed.setPrize(competition.getPrize());
		competitionReconstructed.setRules(competition.getRules());

		this.validator.validate(competitionReconstructed, binding);

		Assert.isTrue(!binding.hasErrors());

		return competitionReconstructed;
	}
}
