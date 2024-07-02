package com.interactive.hana.global.config.security.exception;
import com.google.gson.Gson;

import com.interactive.hana.domain.user.exception.UserExceptionMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        Map<String, String> error = new HashMap<>();
        Gson gson = new Gson();
        Object exception = httpServletRequest.getAttribute("exception");
        if (exception != null) {
            httpServletResponse.setStatus(400);
            if (exception.equals(ErrorCode.WITHDREW)) {
                error.put("error-message", UserExceptionMessage.WITHDRAWAL_ACCOUNT_EXCEPTION_MESSAGE.getMessage());
                String message = gson.toJson(error);
                httpServletResponse.getWriter().println(message);
            } else if (exception.equals(ErrorCode.NO_LOGIN)) {
                error.put("error-message", UserExceptionMessage.NO_LOGIN_EXCEPTION_MESSAGE.getMessage());
                String message = gson.toJson(error);
                httpServletResponse.getWriter().println(message);
            }
        }
    }

}
