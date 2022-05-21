package com.nhnacademy.jdbc.board.compre.filter.defender;

public interface Defender {
    public abstract void init(String[] values);
    public abstract String doFilter(String value);
}
