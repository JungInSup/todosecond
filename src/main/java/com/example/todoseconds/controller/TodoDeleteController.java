package com.example.todoseconds.controller;

import com.example.todoseconds.dao.TodoDAO;
import com.example.todoseconds.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TodoDeleteController", value = "/todo/delete")
public class TodoDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tno = request.getParameter("tno");

        TodoDAO.INSTANCE.Delete(tno);

        response.sendRedirect("/todo/list");
    }
}
