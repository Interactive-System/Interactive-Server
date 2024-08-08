package com.interactive.hana.global.config.security.jwt;

public interface JwtProperties {
    String SECRET_KEY = "INTERACTIVEHANA";
    int EXPIRATION_TIME = 1000 * 60 * 60;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
