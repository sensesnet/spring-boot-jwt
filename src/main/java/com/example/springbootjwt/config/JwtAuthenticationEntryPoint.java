package com.example.springbootjwt.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * This class will extend Spring's AuthenticationEntryPoint class and override its method commence.
 * It rejects every unauthenticated request and send error code 401
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest httpRequest,
                         HttpServletResponse httpResponse,
                         AuthenticationException e) throws IOException, ServletException {

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
