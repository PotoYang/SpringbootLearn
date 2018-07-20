package com.potoyang.learn.securityoauth2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/19 10:42
 * Modified By:
 * Description:
 */
public class OAuth2AuthorizationServerConfiguration
        extends AuthorizationServerConfigurerAdapter {
    private final BaseClientDetails details;
    private final AuthenticationManager authenticationManager;

    public OAuth2AuthorizationServerConfiguration(BaseClientDetails details,
                                                  AuthenticationManager authenticationManager) {
        this.details = details;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder builder = clients
                .inMemory().withClient(this.details.getClientId());
        builder.secret(this.details.getClientSecret())
                .resourceIds(this.details.getResourceIds().toArray(new String[0]))
                .authorities(AuthorityUtils.authorityListToSet(this.details.getAuthorities()).toArray(new String[0]))
                .scopes(this.details.getScope().toArray(new String[0]));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        if (this.details.getAuthorizedGrantTypes().contains("password")) {
            endpoints.authenticationManager(this.authenticationManager);
        }
    }

    @Configuration
    @ConditionalOnMissingBean(BaseClientDetails.class)
    protected static class BaseClientDetailsConfiguration {
        private final OAuth2ClientProperties client;

        protected BaseClientDetailsConfiguration(OAuth2ClientProperties client) {
            this.client = client;
        }

        @Bean
        @ConfigurationProperties(prefix = "sercurity.oauth2.client")
        public BaseClientDetails oauth2ClientDetails() {
            BaseClientDetails details = new BaseClientDetails();
            if (this.client.getClientId() == null) {
                this.client.setClientId(UUID.randomUUID().toString());
            }

            details.setClientId(this.client.getClientId());
            details.setClientSecret(this.client.getClientSecret());
            details.setAuthorizedGrantTypes(Arrays.asList("authorization_code",
                    "password", "client_credentials", "implicit", "refresh_token"));
            details.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            details.setRegisteredRedirectUri(Collections.emptySet());
            return details;
        }
    }
}
