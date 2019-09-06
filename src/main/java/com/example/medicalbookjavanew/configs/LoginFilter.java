package com.example.medicalbookjavanew.configs;

import com.example.medicalbookjavanew.entity.User;
import com.example.medicalbookjavanew.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private UserService userDetailsService;

    public LoginFilter(String url, AuthenticationManager authManager, UserService userDetailsService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userDetailsService = userDetailsService;
    }


//Метод який логінить
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        User user = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), User.class);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, "yes".getBytes())
                .compact();

        response.addHeader("Authorization", token);
    }
}
