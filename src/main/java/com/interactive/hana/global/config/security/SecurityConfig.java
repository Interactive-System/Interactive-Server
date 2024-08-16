package com.interactive.hana.global.config.security;

import com.interactive.hana.global.config.security.exception.CustomAuthenticationEntryPoint;
import com.interactive.hana.global.config.security.jwt.JwtAuthenticationFilter;
import com.interactive.hana.global.config.security.jwt.JwtAuthorizationFilter;
import com.interactive.hana.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(corsFilter, SecurityContextPersistenceFilter.class)
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider))
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        http
                .authorizeRequests()
                .antMatchers("/api/v1/**/dashboard/**").permitAll() // dashboard가 포함된 URL에 대한 접근 허용
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/v1/email-auth").permitAll()
                .antMatchers("/api/v1/image/**").permitAll()
                .antMatchers("/api/v1/file-save").permitAll()
                .antMatchers("/actuator/prometheus").permitAll()
                .antMatchers("/api/v1/user/**")
                .hasAnyRole("USER", "UW", "INSURANCE_PLANNER","INSURANCE_SELLER", "INSURANCE_COMPENSATION_PLANNER",
                        "INSURANCE_COMPENSATION_HANDLER", "CONTRACT_MANAGER", "FINANCIAL_SUPERVISORY_SERVICE", "ADMIN")
                .antMatchers("/api/v1/uw/**")
                .hasAnyRole("UW", "ADMIN")
                .antMatchers("/api/v1/seller/**")
                .hasAnyRole("INSURANCE_SELLER", "ADMIN")
                .antMatchers("/api/v1/planner/**")
                .hasAnyRole("INSURANCE_PLANNER", "ADMIN")
                .antMatchers("/api/v1/financial/supervisory/**")
                .hasAnyRole("FINANCIAL_SUPERVISORY_SERVICE", "ADMIN")
                .antMatchers("/api/v1/compensation-handler/**")
                .hasAnyRole("INSURANCE_COMPENSATION_HANDLER", "ADMIN")
                .antMatchers("/api/v1/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll() // Swagger UI 및 OpenAPI 문서에 대한 접근 허용
                .anyRequest()
                .authenticated();
    }
}
