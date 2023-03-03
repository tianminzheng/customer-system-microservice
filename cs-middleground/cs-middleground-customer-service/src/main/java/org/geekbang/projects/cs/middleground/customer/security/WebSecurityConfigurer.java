package org.geekbang.projects.cs.middleground.customer.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication().withUser("cs_user").password("{noop}password1").roles("USER").and()
			.withUser("cs_admin").password("{noop}password2").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//禁用跨站请求伪造
		http
			.csrf()
			.disable()
			.httpBasic();

		http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET,"/outsourcingSystems/*").permitAll()
			.mvcMatchers(HttpMethod.POST,"/outsourcingSystems/*").hasRole("USER")
			.mvcMatchers(HttpMethod.DELETE, "/outsourcingSystems/*").hasRole("ADMIN")
			.anyRequest().authenticated();
	}
}
