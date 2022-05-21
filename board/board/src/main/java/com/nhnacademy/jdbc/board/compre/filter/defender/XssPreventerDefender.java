package com.nhnacademy.jdbc.board.compre.filter.defender;

import com.nhncorp.lucy.security.xss.XssPreventer;

/**
 * @author todtod80
 */
public class XssPreventerDefender implements Defender {

    /**
     * @param values String[]
     * @return void
     */
    @Override
    public void init(String[] values) {
    }

    /**
     * @param value String
     * @return String
     */
    @Override
    public String doFilter(String value) {
        return XssPreventer.escape(value);
    }
}