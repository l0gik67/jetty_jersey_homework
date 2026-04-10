package by.l0gik67.jettyjersey.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logRequest(httpRequest);
        long startTime = System.currentTimeMillis();
        try {
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logResponse(httpRequest, httpResponse, duration);
        }
    }
    private void logRequest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.info("Запрос: {} - {}", method, requestURI);
    }
    private void logResponse(HttpServletRequest request, HttpServletResponse response, long duration) {
        int statusCode = response.getStatus();
        log.info("Ответ: HTTP {} - {}, время выполнения: {}ms", statusCode, request.getRequestURI(), duration);
    }
}