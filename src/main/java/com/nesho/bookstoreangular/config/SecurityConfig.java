package com.nesho.bookstoreangular.config;

import com.nesho.bookstoreangular.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_MATCHERS = {

            "/css/**",
            "/js/**",
            "/image/**",
            "/book/**",
            "/login/**",
            "/user/**"


    };
    @Autowired
    private Environment env;
    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder PasswordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable().httpBasic().and().authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

//        http.csrf().disable()
//              //  .cors().disable().httpBasic()
//                .authorizeRequests().anyRequest().permitAll()
//            .antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().permitAll();//authenticated();
    }

    @Autowired
    public void configreGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(PasswordEncoder());
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

}
