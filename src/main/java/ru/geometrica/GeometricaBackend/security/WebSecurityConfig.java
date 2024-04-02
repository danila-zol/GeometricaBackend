package ru.geometrica.GeometricaBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.geometrica.GeometricaBackend.model.UserRepository;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig {
	private static final String[] AUTH_WHITELIST = {
			// -- swagger ui
			"/v2/api-docs",
			"/v3/api-docs",
			"/swagger-ui/**",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**"
	};

	@Bean
	public SecurityFilterChain filterChain( HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers(
								"/v2/api-docs/**",
								"/v3/api-docs/**",
								"/swagger-ui.html",
								"/swagger-ui/**",
								"/swagger-resources/**",
								"/swagger-resources").permitAll()
						.requestMatchers("/customers/register", "/test").permitAll()
						.requestMatchers(HttpMethod.POST).authenticated()
						.requestMatchers(HttpMethod.GET).permitAll() // Change to authenticated after defence
				)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new GeometricaUserDetailsService();
	}

//	@Bean
//	@Autowired
//	public JdbcUserDetailsManager user(DataSource dataSource) {
//
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("adm_psw")
//				.roles("ADMIN")
//				.authorities("ADMIN")
//				.build();
//
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		jdbcUserDetailsManager.createUser(admin);
//		return jdbcUserDetailsManager;
//	}

	@Bean
	public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
		return new BCryptPasswordEncoder();
	}

}
