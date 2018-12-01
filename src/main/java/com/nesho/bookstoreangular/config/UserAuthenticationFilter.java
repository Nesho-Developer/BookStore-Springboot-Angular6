package com.nesho.bookstoreangular.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	@Autowired
//	UserRepository userDao;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        return this.getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(obtainUsername(request), obtainPassword(request)));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        chain.doFilter(request, response);
//		User user = userDao.findByUsername(authResult.getName());
//		user.setLastLoginDate(new Date());
//		userDao.save(user);

    }
}
