package br.com.upboxserver;

import br.com.upboxserver.v1.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class UpboxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpboxServerApplication.class, args);
	}

}
