package max.lab.springboot.latest.singleweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SingleWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleWebApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/secured-page")
	public String securedPage() {
		return "secured-page";
	}
}
