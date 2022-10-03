package com.example.todoseconds.controller;

import com.example.todoseconds.dao.MyBatisUtil;
import com.example.todoseconds.dao.TodoDAO;
import com.example.todoseconds.dto.TodoDTO;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "TodoUpdateController", value = "/todo/update")
public class TodoUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tno = request.getParameter("tno");

        TodoDTO dto = TodoDAO.INSTANCE.Select(tno);

        request.setAttribute("todo", dto);


        request.getRequestDispatcher("/WEB-INF/views/todo/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tno = request.getParameter("tno");
        String title = request.getParameter("title");
        String memo = request.getParameter("memo");
        String dueDate = request.getParameter("dueDate");
        String complete = request.getParameter("complete");

        System.out.println("Update tno " + tno);

        TodoDTO dto = TodoDTO.builder()
                .tno(Integer.parseInt(tno))
                .title(title)
                .memo(memo)
                .dueDate(LocalDate.parse(dueDate))
                .complete(complete == null ? false : true)
                .build();

        SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        session.update("com.example.todoseconds.dao.TodoMapper.update", dto);

        response.sendRedirect("/todo/list");

    }
}
