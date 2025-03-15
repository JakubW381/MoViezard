package dev.aamgasi.MovieWizard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MovieWizardApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieWizardApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Witaj na stronie głównej!";
	}
}
