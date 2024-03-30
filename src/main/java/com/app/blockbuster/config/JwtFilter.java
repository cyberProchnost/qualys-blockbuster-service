package com.app.blockbuster.config;

import com.app.blockbuster.entity.User;
import com.app.blockbuster.enums.Role;
import com.app.blockbuster.exception.ErrorMessage;
import com.app.blockbuster.exception.InvalidAuthenticationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            try {
                throw new InvalidAuthenticationException("Invalid Or No Authentication token");
            } catch (InvalidAuthenticationException ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                ErrorMessage errorMessage = ErrorMessage.builder().
                        statusCode(HttpStatus.UNAUTHORIZED.value()).
                        timestamp(new Date()).message(ex.getMessage()).build();
                PrintWriter writer = response.getWriter();
                writer.write(convertObjectToJson(errorMessage));
                writer.flush();
                return;
            }
        }
        final String token = authHeader.substring(7);
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ErrorMessage errorMessage = ErrorMessage.builder().
                    statusCode(HttpStatus.UNAUTHORIZED.value()).
                    timestamp(new Date()).message("Token expired or invalid token").build();
            PrintWriter writer = response.getWriter();
            writer.write(convertObjectToJson(errorMessage));
            writer.flush();
            return;
        }
        String[] userDetails = claims.getSubject().split("::");
        User user = User.builder().email(userDetails[0]).name(userDetails[1]).
                role(userDetails[2].equals("ADMIN") ? Role.ADMIN : Role.USER).id(Long.parseLong(userDetails[3])).build();
        request.setAttribute("user", user);
        filterChain.doFilter(request, response);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
