package br.edu.ifce.academico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfiguration {

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/resources/**", "/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**");
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> 
			requests.antMatchers("/sobre").permitAll().anyRequest().authenticated().and()
		)
		.formLogin().and().exceptionHandling().accessDeniedPage("/403");
		
		//antMatchers("/cursos/cadastrar").hasRole("USER")
		
		return http.build();
	}
	
	@Bean
	public MyUserDetails customUserDetailsService() {
		return new MyUserDetails();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
