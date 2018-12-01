package com.nesho.bookstoreangular.config;

import com.nesho.bookstoreangular.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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

    private PasswordEncoder PasswordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/error", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/**/favicon.ico"
                        , "/configuration/security", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .httpBasic().and().authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("/user/**").hasAnyAuthority("USER")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/error.html");

//        http.csrf().disable()
//              //  .cors().disable().httpBasic()
//                .authorizeRequests().anyRequest().permitAll()
//            .antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().permitAll();//authenticated();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(PasswordEncoder());
    }

    @Bean
    public UserAuthenticationFilter userAuthenticationFilter() throws Exception {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter();
        userAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        userAuthenticationFilter
                .setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/bookstore/oauth/token", "POST"));
        return userAuthenticationFilter;
    }


//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new HeaderHttpSessionStrategy();
//    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123#4@%^&*()*&^%$443ggbffx");
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
