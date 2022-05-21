package com.nhnacademy.jdbc.board.compre.filter.defender;

import com.nhncorp.lucy.security.xss.XssFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author todtod80
 */
public class XssFilterDefender implements Defender {
    private XssFilter filter;

    /**
     * @param values String[]
     * @return void
     */
    @Override
    public void init(String[] values) {
        if (values == null || values.length == 0) {
            filter = XssFilter.getInstance();
        } else {
            switch (values.length) {
                case 1:
                    if (isBoolean(values[0])) {
                        filter = XssFilter.getInstance(convertBoolean(values[0]));
                    } else {
                        filter = XssFilter.getInstance(values[0]);
                    }
                    break;
                case 2:
                    filter = XssFilter.getInstance(values[0], convertBoolean(values[1]));
                    break;
                default:
                    filter = null;
                    break;
            }
        }
    }

    /**
     * @param value String
     * @return String
     */
    @Override
    public String doFilter(String value) {
        return filter.doFilter(value);
    }

    /**
     * @param value String
     * @return boolean
     */
    private boolean isBoolean(String value) {
        return StringUtils.equalsIgnoreCase(value, "true") || StringUtils.equalsIgnoreCase(value, "false");
    }

    /**
     * @param value String
     * @return boolean
     */
    private boolean convertBoolean(String value) {
        return StringUtils.equalsIgnoreCase(value, "true") ? true : false;
    }
}