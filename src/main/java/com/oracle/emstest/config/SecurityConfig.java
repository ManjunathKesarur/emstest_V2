package com.oracle.emstest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
@Bean	
UserDetailsService detailsService(PasswordEncoder encoder) {
	UserDetails admin = User.withUsername("admin").password(encoder.encode("admin@123")).roles("ADMIN").build();
	UserDetails users = User.withUsername("user").password(encoder.encode("user@123")).roles("USER").build();
	
	return new InMemoryUserDetailsManager(admin,users);
}
	
	
@Bean
SecurityFilterChain filterChain(HttpSecurity http) {
http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
				.requestMatchers("/users/**")
				.permitAll()
				.requestMatchers(HttpMethod.GET,"/employees/**")
				.hasAnyRole("ADMIN","USER")
				.requestMatchers("/employees/**")
				.hasRole("ADMIN"));

	http.httpBasic(Customizer.withDefaults());
	return http.build();
}


}
