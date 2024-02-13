package fi.haagahelia.customer;

import fi.haagahelia.customer.domain.Customer;
import fi.haagahelia.customer.domain.CustomerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CustomerApplication {

	private static final Logger log = LoggerFactory.getLogger(CustomerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	// Database is created by using resources/schema.sql
	@Bean
	public CommandLineRunner demo(CustomerDAOImpl customerDAO){

		return (args) -> {


			// Insert some demo data
			customerDAO.save(new Customer("John", "West"));
			customerDAO.save(new Customer("Mike", "Mars"));
			customerDAO.save(new Customer("Kate", "Johnson"));

		};
	}
}



