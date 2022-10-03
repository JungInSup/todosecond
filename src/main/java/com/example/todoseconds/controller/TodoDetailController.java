package com.example.todoseconds.controller;

import com.example.todoseconds.dao.TodoDAO;
import com.example.todoseconds.dto.TodoDTO;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TodoDetailController", value = "/todo/detail")
public class TodoDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 파라미터의 tno를 가져옴
        String tno = request.getParameter("tno");

        // tno에 대한 정보를 가져오고 정보를 setAttribute로 이름가 value로 jsp로 넘겨준다.
        TodoDTO dto = TodoDAO.INSTANCE.Select(tno);

        request.setAttribute("onetodo", dto);
        // jsp에 대한 value를 뿌려줌

        request.getRequestDispatcher("/WEB-INF/views/todo/detail.jsp").forward(request, response);


    }

}
