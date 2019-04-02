package max.lab.springboot.latest.userweb.config;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by max on 19-4-2.
 */
@Configuration
public class FeignConfigurer {
    @Bean
    RequestInterceptor requestInterceptor(OAuth2ClientContext clientContext,
                                          OAuth2ProtectedResourceDetails resourceDetails) {
        OAuth2FeignRequestInterceptor requestInterceptor = new OAuth2FeignRequestInterceptor(
                clientContext, resourceDetails);
        return requestInterceptor;
    }
}
