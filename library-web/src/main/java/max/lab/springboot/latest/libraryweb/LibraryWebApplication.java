package max.lab.springboot.latest.libraryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableZuulProxy
@ComponentScan("max.lab.springboot.latest.web")
public class LibraryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryWebApplication.class, args);
	}

}
