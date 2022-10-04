package com.example.todoseconds.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //브라우저에서 session을 받았는데 아무것도 없는 경우
      HttpSession session = request.getSession(false);

      // 로그아웃을 할경우 f12 -> id를 삭제하거나 브라우저를 닫을경우(?)
      // sendRedircet로 /hello로 가게 된다.
      if (session == null) {

          response.sendRedirect("/");
          return;
      }

      session.invalidate(); // invalidate->무효화 // 세션이 더이상 유효하지 않다.
      session.setMaxInactiveInterval(0); // setMaxInactiveInterval 괄호안의 시간만큼 활동을 하지 않으면 무효화를 해준다.

    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
