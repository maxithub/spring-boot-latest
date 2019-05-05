package max.lab.springboot.latest.oauth2serverweb;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by max on 19-5-5.
 */
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user1")
                .password(passwordEncoder.encode("secret"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("secret"))
                .roles("ADMIN");
    }
}
