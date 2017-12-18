
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Report;
import domain.Trainer;

@Transactional
@Service
public class ReportService {

	//Repository
	//======================================================================

	@Autowired
	private ReportRepository	reportRepository;

	//Services
	// ======================================================================

	@Autowired
	private TrainerService		trainerService;


	//CRUD methods
	//=======================================================================

	public Report findOne(final int id) {
		Report report;

		report = this.reportRepository.findOne(id);
		Assert.notNull(report);

		return report;
	}

	public Report create() {
		final Report report = new Report();

		Assert.isInstanceOf(Trainer.class, this.trainerService.findByPrincipal());

		return report;
	}

	public Report save(final Report report) {
		Assert.notNull(report);
		Assert.isInstanceOf(Trainer.class, this.trainerService.findByPrincipal());

		final Report reportRes = this.reportRepository.save(report);

		return reportRes;
	}

	public Collection<Report> findAll() {

		Collection<Report> reports;

		reports = this.reportRepository.findAll();
		Assert.notNull(reports);

		return reports;
	}

	//Other bussiness methods
	// ==================================================================

}
