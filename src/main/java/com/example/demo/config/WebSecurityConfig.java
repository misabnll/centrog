package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserDetailsServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	private static String[] resources = {"/css/**", "/img/**", "/js/**", "/vendor/**", "/download/**"};
	
	@Autowired
    UserDetailsServiceImpl userService;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
	
	static BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(31);
//		System.out.println(bCryptPasswordEncoder.encode("centrog"));
        return bCryptPasswordEncoder;
    }

	@Configuration
	@Order(1)
	public static class configurationAdapter1 extends WebSecurityConfigurerAdapter {
		public configurationAdapter1() {
            super();
        }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
				.antMatchers(resources).permitAll()
				.antMatchers(HttpMethod.GET, "/", "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers("/centrog/**" + "/**").access("hasRole('ROLE_USER')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/centrog")
				.failureUrl("/login/?error=true")
				.usernameParameter("txtUsername")
	            .passwordParameter("txtPassword")
	            .and()
	        .logout()
	        	.permitAll()
	        	.logoutSuccessUrl("/login?logout");
	    }
	}
}
