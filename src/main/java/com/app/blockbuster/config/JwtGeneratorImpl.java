package com.app.blockbuster.config;

import com.app.blockbuster.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {
    @Override
    public Map<String, String> generateToken(User user) {
        String jwt = "";
        Date issuedAt = new Date();
        Date expiry = new Date(issuedAt.getTime() + 300000);
        String subject = user.getEmail() + "::" + user.getName() + "::" + user.getRole().name() + "::" + user.getId();
        jwt = Jwts.builder().setSubject(subject).setIssuedAt(issuedAt).setExpiration(expiry).
                signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", jwt);
        return tokenMap;
    }
}
