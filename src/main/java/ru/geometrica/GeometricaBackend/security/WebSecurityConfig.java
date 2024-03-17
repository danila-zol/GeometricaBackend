package ru.geometrica.GeometricaBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain( HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((requests) -> requests
						.anyRequest().permitAll()
				)
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	@Autowired
	public JdbcUserDetailsManager user(DataSource dataSource) {

		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("adm_psw")
				.roles("ADMIN")
				.authorities("ADMIN")
				.build();

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(admin);
		return jdbcUserDetailsManager;
	}
}