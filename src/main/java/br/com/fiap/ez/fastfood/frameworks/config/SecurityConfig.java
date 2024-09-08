
package br.com.fiap.ez.fastfood.frameworks.config;

/*import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "security.config.enabled", havingValue = "true", matchIfMissing = true)
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() 
						.requestMatchers("/api/public/**").permitAll() // Permit access to public APIs
						.requestMatchers("/api/customers/login").permitAll() // Permit access to login endpoint
						.anyRequest().permitAll() // Allow all other requests
				).formLogin(form -> form.disable()) // Disable form login
				.httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication

		return http.build();
	}
}*/