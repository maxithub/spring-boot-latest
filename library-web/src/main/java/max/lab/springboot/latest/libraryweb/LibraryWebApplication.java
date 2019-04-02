package max.lab.springboot.latest.libraryweb;

import feign.RequestInterceptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@SpringCloudApplication
@Controller
//@EnableWebMvc
public class LibraryWebApplication {

	@Bean
	RequestInterceptor requestInterceptor(OAuth2ClientContext clientContext,
										  OAuth2ProtectedResourceDetails resourceDetails) {
		OAuth2FeignRequestInterceptor requestInterceptor = new OAuth2FeignRequestInterceptor(
				clientContext, resourceDetails);
		return requestInterceptor;
	}


	@GetMapping("/")
	public String index() {
		return "index";
	}

	@Bean
	public CommandLineRunner init(BookService bookService,
								  UserService userService) {
		return (args -> {
			System.out.println("************************************");
			bookService.findAllBooks().forEach(System.out::println);
			userService.findAllUsers().forEach(System.out::println);
			System.out.println("************************************");
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryWebApplication.class, args);
	}

}
