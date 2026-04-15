package by.l0gik67.jettyjersey.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final int CACHE_LIMIT = 1024*1024;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, CACHE_LIMIT);

        filterChain.doFilter(wrappedRequest, response);

        log.info("uri={}, method={}, contentType={}, body={}, time={}",
                request.getRequestURI(),
                request.getMethod(),
                request.getContentType(),
                wrappedRequest.getContentAsString(),
                LocalDateTime.now());
    }
}