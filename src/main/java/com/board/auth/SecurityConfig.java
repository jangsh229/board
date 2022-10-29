package com.board.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private CustomAuthFailureHandler customAuthFailureHandler; 
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
            "/webjars/**");
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers( //정적 리소스
            		"/resources/**"
            		).permitAll()
            .antMatchers( //회원 허용 url
            		"/board/detail"
            		).hasRole("USER")
            .antMatchers( //전체 허용 url (권한이 필요한 url보다 밑에 작성되어야 함)
            		"/**"
            		).permitAll()
            .anyRequest().authenticated()
            .and()
	            .formLogin()
	            .usernameParameter("mem_id")
	            .passwordParameter("mem_pwd")
	            .loginPage("/member/login")
	            .loginProcessingUrl("/authenticate")
	            .failureHandler(customAuthFailureHandler)
	        .and()
	            .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/");
    }
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
