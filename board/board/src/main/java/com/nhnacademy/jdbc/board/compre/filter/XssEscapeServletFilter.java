package com.nhnacademy.jdbc.board.compre.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author todtod80
 * @author leeplay
 */
public class XssEscapeServletFilter implements Filter {

    private XssEscapeFilter xssEscapeFilter = XssEscapeFilter.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssEscapeServletFilterWrapper(request, xssEscapeFilter), response);
    }

    @Override
    public void destroy() {
    }
}