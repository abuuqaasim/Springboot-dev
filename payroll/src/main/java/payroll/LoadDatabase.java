package payroll;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repo) {
		
		return args -> {
			log.info("Pre loading " + repo.save(new Employee2("Ridwan Atolagbe", "Father")));
			log.info("Preloading "+ repo.save(new Employee2("Bisola", "Mother")));
		};
		
	}
	
	
	
}
