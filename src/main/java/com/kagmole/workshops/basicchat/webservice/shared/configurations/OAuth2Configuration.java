package com.kagmole.workshops.basicchat.webservice.shared.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.kagmole.workshops.basicchat.webservice.users.UserService;

@Configuration
public class OAuth2Configuration {
	
	private static final String MY_RESOURCE_APP_ID = "basic-chat-web-service";
	
	private static final String MY_CLIENT_ID = MY_RESOURCE_APP_ID + "/web-client";
	
	private static final String MY_CLIENT_SECRET = "reallyStrongPa55word!";
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		
		// Resource server configuration (configure accessible paths)
		
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			
			resources
				.resourceId(MY_RESOURCE_APP_ID)
				.stateless(true);
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			
			http
				.requestMatchers()
				// Paths this resource server handles
				.antMatchers("/messages/**", "/users/**")
				// Etc.
			.and()
				.authorizeRequests()
				// Rights attribution
				
				.antMatchers(HttpMethod.GET, "/messages/**", "/users/**")
					.access("#oauth2.clientHasRole('TRUSTED_CLIENT') and #oauth2.hasScope('read')")
					
				.antMatchers(HttpMethod.POST, "/users")
					.access("#oauth2.clientHasRole('TRUSTED_CLIENT') and hasAuthority('ADMINISTRATION')")
					
				.antMatchers("/messages/**", "/users/**")
					.access("#oauth2.clientHasRole('TRUSTED_CLIENT') and #oauth2.isUser()")
					
				.anyRequest()
					.denyAll();
		}
	}
	
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration 
			extends AuthorizationServerConfigurerAdapter {
		
		// Authorization server configuration (configure authorization attribution)
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private DataSource dataSource;
		
		@Autowired
		private UserService userService;
		
		@Bean
		public TokenStore tokenStore() {
			
			JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);
			
			return tokenStore;
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			
			endpoints
				.tokenStore(tokenStore())
				
				// Mandatory if you use the default "refresh_token" or "password" token granter
				.authenticationManager(authenticationManager)
				.userDetailsService(userService);
		}
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			
			clients
				.inMemory()
					.withClient(MY_CLIENT_ID)
					.secret(MY_CLIENT_SECRET)
					.resourceIds(MY_RESOURCE_APP_ID)
					.authorizedGrantTypes("client_credentials", "password", "refresh_token")
					.authorities("TRUSTED_CLIENT")
					.scopes("read", "write", "trust")
					.accessTokenValiditySeconds(60 * 5)
					.refreshTokenValiditySeconds(60 * 60);
		}
	}
}
