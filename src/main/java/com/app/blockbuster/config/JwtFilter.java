package com.app.blockbuster.config;

import com.app.blockbuster.entity.User;
import com.app.blockbuster.enums.Role;
import com.app.blockbuster.exception.InvalidAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new InvalidAuthenticationException("Invalid Or No Authentication token");
        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        String[] userDetails = claims.getSubject().split("::");
        User user = User.builder().email(userDetails[0]).name(userDetails[1]).
                role(userDetails[2].equals("ADMIN") ? Role.ADMIN : Role.USER).id(Long.parseLong(userDetails[3])).build();
        request.setAttribute("user", user);
        filterChain.doFilter(request, response);
    }
}
