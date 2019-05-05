package max.lab.springboot.latest.oauth2serverweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

/**
 * Created by max on 19-5-5.
 */
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    public AuthorizationServerConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("secret"))
                .scopes("resource1:read")
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:8081/oauth/login/client-app");
    }
}
