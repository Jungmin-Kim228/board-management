package com.nhnacademy.jdbc.board.compre.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author todtod80
 * @author leeplay
 */
public class XssEscapeServletFilterWrapper extends HttpServletRequestWrapper {
    private XssEscapeFilter xssEscapeFilter;
    private String path = null;

    public XssEscapeServletFilterWrapper(ServletRequest request, XssEscapeFilter xssEscapeFilter) {
        super((HttpServletRequest)request);
        this.xssEscapeFilter = xssEscapeFilter;

        String contextPath = ((HttpServletRequest) request).getContextPath();
        this.path = ((HttpServletRequest) request).getRequestURI().substring(contextPath.length());
    }

    @Override
    public String getParameter(String paramName) {
        String value = super.getParameter(paramName);
        return doFilter(paramName, value);
    }

    @Override
    public String[] getParameterValues(String paramName) {
        String values[] = super.getParameterValues(paramName);
        if (values == null) {
            return values;
        }
        for (int index = 0; index < values.length; index++) {
            values[index] = doFilter(paramName, values[index]);
        }
        return values;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = super.getParameterMap();
        Map<String, String[]> newFilteredParamMap = new HashMap<>();

        Set<Map.Entry<String, String[]>> entries = paramMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String paramName = entry.getKey();
            Object[] valueObj = entry.getValue();
            String[] filteredValue = new String[valueObj.length];
            for (int index = 0; index < valueObj.length; index++) {
                filteredValue[index] = doFilter(paramName, String.valueOf(valueObj[index]));
            }

            newFilteredParamMap.put(entry.getKey(), filteredValue);
        }

        return newFilteredParamMap;
    }

    /**
     * @param paramName String
     * @param value String
     * @return String
     */
    private String doFilter(String paramName, String value) {
        return xssEscapeFilter.doFilter(path, paramName, value);
    }
}