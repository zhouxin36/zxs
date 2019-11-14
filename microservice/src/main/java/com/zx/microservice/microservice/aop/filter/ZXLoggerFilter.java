package com.zx.microservice.microservice.aop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zhouxin
 * @date 2018-12-14
 */
@WebFilter(value = "/getEnv",filterName = "loggerFilter")
public class ZXLoggerFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(ZXLoggerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.warn("---------->ZXLoggerFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        logger.warn("---------->ZXLoggerFilter.doFilter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
//        logger.warn("---------->ZXLoggerFilter.destroy");
    }
}
