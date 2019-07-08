package br.com.upboxserver.upboxserver;

import br.com.upboxserver.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@ComponentScan("br.com.upboxserver")
public class UpboxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpboxServerApplication.class, args);
	}

}
