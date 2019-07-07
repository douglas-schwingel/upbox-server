package br.com.upboxserver.upboxserver;

import br.com.upboxserver.config.SwaggerConfig;
import br.com.upboxserver.controller.UsuarioController;
import br.com.upboxserver.repository.UsuarioRepository;
import br.com.upboxserver.retrofit.GoogleWebClient;
import br.com.upboxserver.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SwaggerConfig.class, UsuarioController.class,
		UsuarioRepository.class, GoogleWebClient.class, UserService.class})
public class UpboxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpboxServerApplication.class, args);
	}

}
