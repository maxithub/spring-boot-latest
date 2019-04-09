package max.lab.springboot.latest.web.config;

import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

/**
 * Created by max on 19-4-2.
 */
@Configuration
public class WebConfigurer {
    @Bean
    @ConditionalOnEnabledResourceChain
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }
}
