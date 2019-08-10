package max.lab.springboot.latest.oauth2serverweb;

import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerTokenServicesConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Created by max on 19-5-5.
 */
@EnableAuthorizationServer
@Configuration
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@Import(AuthorizationServerTokenServicesConfiguration.class)
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final JwtAccessTokenConverter tokenConverter;

    public AuthorizationServerConfigurer(PasswordEncoder passwordEncoder,
                                         JwtAccessTokenConverter tokenConverter) {
        this.passwordEncoder = passwordEncoder;
        this.tokenConverter = tokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("secret"))
//                .scopes("resource1:read")
                .authorizedGrantTypes("authorization_code")
                .scopes("user-info")
                .autoApprove(true)
                .authorities("APP_CLIENT")
                .redirectUris("http://localhost:8019/secured-page");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(tokenConverter);
    }

    @FrameworkEndpoint
    static class Endpoints {
        @GetMapping("/user/me")
        public Principal user(Principal principal) {
            return principal;
        }
    }
}
