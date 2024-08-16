package com.interactive.hana.global.config.security.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.interactive.hana.domain.user.exception.NoLoginException;
import com.interactive.hana.domain.user.exception.WithdrawalAccountException;
import com.interactive.hana.global.config.security.exception.ErrorCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        super(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();

        logger.info("Request URL - " + request.getRequestURL());

        if (pathMatcher.match("/api/v1/**/dashboard/**", requestUri) ||
                pathMatcher.match("/api/v1/image/**", requestUri) ||
                pathMatcher.match("/v3/api-docs/**", requestUri) ||
                pathMatcher.match("/swagger-ui/**", requestUri) ||
                requestUri.equals("/signup") ||
                requestUri.equals("/login") ||
                requestUri.equals("/api/v1/email-auth") ||
                requestUri.equals("/api/v1/file-save") ||
                requestUri.equals("/swagger-ui.html") ||
                requestUri.equals("/actuator/prometheus") ||
                requestUri.equals("/favicon.ico")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String username = jwtTokenProvider.validateToken(request);
            if (!username.equals("")) {
                String token = jwtTokenProvider.createToken(username);
                response.addHeader("Authorization", "Bearer " + token);
            }
        } catch (WithdrawalAccountException e) {
            request.setAttribute("exception", ErrorCode.WITHDREW);
        } catch (NoLoginException | TokenExpiredException e) {
            request.setAttribute("exception", ErrorCode.NO_LOGIN);
        }

        chain.doFilter(request, response);
    }

}
