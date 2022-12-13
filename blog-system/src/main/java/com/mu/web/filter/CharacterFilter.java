package com.mu.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-10-15 19:33
 * @description : 字符过滤器
 **/

public class CharacterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
