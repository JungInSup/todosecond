package com.example.todoseconds.controller;

import com.example.todoseconds.dao.TodoDAO;
import com.example.todoseconds.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<TodoDTO> listdto = TodoDAO.INSTANCE.SelectAll();

        request.setAttribute("list_all", listdto);

        request.getRequestDispatcher("/WEB-INF/views/todo/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
