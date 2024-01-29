package org.example.authservice.config;

import jakarta.servlet.http.HttpServletRequest;

public class RequestMatcherUtil {

    public static boolean isAuthEndpoint(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/auth/");
    }

    public static boolean isAdminEndpoint(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/admin/");
    }

    public static boolean isCabDriverEndpoint(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/cab_driver/");
    }

    public static boolean isPassengerEndpoint(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/passenger/");
    }

    public static boolean isSignupEndpoint(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/signup/");
    }
}

