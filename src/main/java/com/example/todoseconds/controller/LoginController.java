package com.example.todoseconds.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log4j2
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("login get.......");

        // login으로 Dispatcher를 login.jsp 로 보냄
        request.getRequestDispatcher("WEB-INF/views/member/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("login post");

        // login.jsp에 있는 name=mid와 name=mpw를 가지고온다.
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        log.info(mid);
        log.info(mpw);

        // 로그인 성공
        if(mid.equals("aaa") && mpw.equals("111")) {

            // 서버에 내가 가지고 있는 세션을 보내서 락커룸에서 정보를 가져옴
            HttpSession session = request.getSession();

            // 로그인 성공 - 사용자의 정보를 담는다.
            session.setAttribute("loginResult", "success");

            if(session.getAttribute("wanted") != null){

                // test in
                if(session.getAttribute("wanted") == "/todo/detail") {

                    session.setAttribute("detail", "/todo/detail");
                    return;
                }

                response.sendRedirect((String)session.getAttribute("wanted"));
                session.removeAttribute("wanted");

            }else {
                response.sendRedirect("/todo/list");
            }
            return;

        }

        // 로그인 실패시
        log.info("login failed.....");
        // 실패정보로 파라미터를 error=fail을 붙힌다.
        response.sendRedirect("/login?error=fail");
    }
}
