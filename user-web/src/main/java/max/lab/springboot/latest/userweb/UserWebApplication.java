package max.lab.springboot.latest.userweb;

import max.lab.springboot.latest.web.config.EnableCustomWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
//@EnableCustomWeb
@ComponentScan("max.lab.springboot.latest.web")
public class UserWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserWebApplication.class, args);
    }
}
