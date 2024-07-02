package com.interactive.hana.global.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.interactive.hana.domain.user.dao.UserRepository;
import com.interactive.hana.domain.user.exception.EmailNotFoundException;
import com.interactive.hana.domain.user.exception.NoLoginException;
import com.interactive.hana.domain.user.exception.UserExceptionMessage;
import com.interactive.hana.domain.user.exception.WithdrawalAccountException;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final UserRepository userRepository;

    public String createToken(String username) {
        return JWT.create()
                .withSubject("jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET_KEY));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String validateToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith(JwtProperties.TOKEN_PREFIX))
            throw new NoLoginException(UserExceptionMessage.NO_LOGIN_EXCEPTION_MESSAGE);

        String username = getUsername(authorization);
        if (username != null) {
            PrincipalDetails principalDetails = new PrincipalDetails(userRepository.findByEmail(username)
                    .orElseThrow(() -> new EmailNotFoundException(UserExceptionMessage.EMAIL_NOT_FOUND_EXCEPTION_MESSAGE)));
            if (!principalDetails.isEnabled())
                throw new WithdrawalAccountException(UserExceptionMessage.WITHDRAWAL_ACCOUNT_EXCEPTION_MESSAGE);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return username;
    }

    private String getUsername(String authorization) {
        String jwtToken = authorization.replace(JwtProperties.TOKEN_PREFIX, "");
        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY)).build()
                .verify(jwtToken)
                .getClaim("username")
                .asString();
    }

}
