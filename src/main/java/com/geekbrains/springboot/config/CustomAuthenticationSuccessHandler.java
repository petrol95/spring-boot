package com.geekbrains.springboot.config;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public abstract class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
}