
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConfigurationSystemRepository;
import domain.ConfigurationSystem;

@Service
@Transactional
public class ConfigurationSystemService {

	@Autowired
	private ConfigurationSystemRepository	configurationSystemRepository;


	public ConfigurationSystemService() {
		super();
	}
	
	public ConfigurationSystem save(ConfigurationSystem configurationSystem){
		Assert.notNull(configurationSystem);
		return configurationSystemRepository.save(configurationSystem);
	}
	public ConfigurationSystem findTheOne() {

		Collection<ConfigurationSystem> collection = configurationSystemRepository.findAll();
		Assert.notEmpty(collection);

		return collection.iterator().next();
	}

	public void changeShowAssessments() {
		ConfigurationSystem c = this.findTheOne();
		c.setShowAssessment(!c.getShowAssessment());
		this.save(c);
		
		
	}

}
