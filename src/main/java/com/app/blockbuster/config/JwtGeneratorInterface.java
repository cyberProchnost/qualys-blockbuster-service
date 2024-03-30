package com.app.blockbuster.config;

import com.app.blockbuster.entity.User;

import java.util.Map;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);
}
