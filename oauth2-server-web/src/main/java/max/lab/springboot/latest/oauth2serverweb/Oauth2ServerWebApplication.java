package max.lab.springboot.latest.oauth2serverweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2ServerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServerWebApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return (new BCryptPasswordEncoder());
	}

}
