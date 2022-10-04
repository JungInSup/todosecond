package com.example.todoseconds.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// /todo/* : todo가 포함된 모든것들이 필터링이 된다.
@Log4j2
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/todo/*"})
public class LoginCheckFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

        log.info("init..........");
    }

    public void destroy() {

        log.info("destroy........");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");


        log.info("doFilter................");
        log.info("doFilter................");
        log.info("doFilter................");
        log.info("doFilter................");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        HttpSession session = req.getSession();

        // login을 안하고 /todo/add로 들어가면 다시 login 페이지로 넘어가게 만들고
        // login을 했을때, /todo/add로 브라우저를 띄워주려면 /todo/add를 저장시켜 줘야 한다.
        // 그런 실행을 시켜주는 것이 getRequestURI() 이다.
        String requestURL = req.getRequestURI();
        String requestQuery = req.getQueryString();

        System.out.println(requestQuery);

        // 만약 list에 118번을 찍으면 /todo/detail만 들어가게 된다.
        if(requestURL.equals("/todo/list")) {

            //doFilter는 그냥 필터를 거쳐서 보내주는것?
            chain.doFilter(request, response);
            return;
        }

        // 처음 왔을때 로그인 화면으로 보내줌
        //if (session == null){

        //    res.sendRedirect("/login");
        //    return;
        //}

        // 처음 오지는 않지는 로그인이 되지 않았을때 다시 로그인 화면으로 보내줌
        if (session.getAttribute("loginResult") == null) {

            // 처음 url을 requestURL로 setAttribute로 설정
            session.setAttribute("wanted", requestURL + "?" + requestQuery);
            res.sendRedirect("/login");
            return;
        }

        // 필터에서 제일 중요한 메서드 : 필터 역할이 끝나면 다음 필터나 목적지로 가게끔 한다.
        chain.doFilter(request, response);


    }
}
