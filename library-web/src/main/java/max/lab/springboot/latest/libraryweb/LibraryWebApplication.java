package max.lab.springboot.latest.libraryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class LibraryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryWebApplication.class, args);
	}

}
