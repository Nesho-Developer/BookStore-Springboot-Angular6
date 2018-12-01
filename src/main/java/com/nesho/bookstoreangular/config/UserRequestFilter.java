package com.nesho.bookstoreangular.config;

import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Component
public class UserRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String auth = request.getHeader("Authorization");
        String token = "";
        if (auth != null && auth.toLowerCase().startsWith("bearer ")) {
            token = auth.replace("Bearer ", "");
            JsonParser objectMapper = JsonParserFactory.create();
            Map<String, Object> claims = objectMapper.parseMap(JwtHelper.decode(token).getClaims());

        }
        filterChain.doFilter(request, response);
    }

}
