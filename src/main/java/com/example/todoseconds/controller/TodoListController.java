package com.example.todoseconds.controller;

import com.example.todoseconds.dao.TodoDAO;
import com.example.todoseconds.dto.PageRequestDTO;
import com.example.todoseconds.dto.PageResponseDTO;
import com.example.todoseconds.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@Log4j2
@WebServlet(name = "TodoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {


    protected int getInt(String value, int defaultValue, Predicate<Integer> predicate){
        try {

            int intValue = Integer.parseInt(value);

            // 람다식이 false가 나올때 defaultValue가 지정된 값 : 1 이 나온다.
            if(predicate != null) {
                boolean result = predicate.test(intValue);

                if (!result) {
                    return defaultValue;
                }
            }

            return intValue;
        }catch(Exception e){
            return defaultValue;
        }
    }
    protected  void redirect(HttpServletResponse response, String queryString) throws ServletException, IOException {
        response.sendRedirect(queryString);
    }


    protected  void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/" +page+ ".jsp").forward(request,response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        List<TodoDTO> listdto = TodoDAO.INSTANCE.SelectAll();
//
//        request.setAttribute("list_all", listdto);


        // --------------------------------------------------------------------------------

        int page = getInt(request.getParameter("page"), 1, (value) -> {
            if(value < 0){
                return false;
            }else {
                if(value > 100){
                    return false;
                }
            }
            return true;
        });

        int size = getInt(request.getParameter("size"), 10, null);

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(page).size(size).build();

        log.info(pageRequestDTO);

        List<TodoDTO> dtoList = TodoDAO.INSTANCE.getList(pageRequestDTO);

        request.setAttribute("dtoList", dtoList);

        PageResponseDTO responseDTO = new PageResponseDTO(pageRequestDTO, TodoDAO.INSTANCE.getTotal(pageRequestDTO));

        request.setAttribute("pageDTO", responseDTO);

        dispatch(request, response, "todo/list");
    }


}
