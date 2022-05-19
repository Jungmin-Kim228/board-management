package com.nhnacademy.jdbc.board.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    List<String> urls = new ArrayList<>();

    private void addList() {
        urls.add("/");
        urls.add("/WEB-INF/views/index/index.html");
        urls.add("/login");
        urls.add("/WEB-INF/views/login/loginForm.html");
        urls.add("/board");
        urls.add("/WEB-INF/views/board/boardView.html");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        addList();
        String requestUri = request.getRequestURI();

        if (!(urls.contains(requestUri)) &&
            (Objects.isNull(request.getSession(false))) &&
            (Objects.isNull(request.getSession(false).getAttribute("id")))) {
            response.sendRedirect("/login");
            }
        return true;
    }
}
