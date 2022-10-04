package com.example.todoseconds;

import com.example.todoseconds.dao.MyBatisUtil;
import com.example.todoseconds.dao.TodoDAO;
import com.example.todoseconds.dto.PageRequestDTO;
import com.example.todoseconds.dto.TodoDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class TodoDAOTests {

    @Test
    public void testList(){

        List<TodoDTO> list = TodoDAO.INSTANCE.getList(PageRequestDTO.builder().build());

        list.forEach(todoDTO -> log.info(todoDTO));
    }

    @Test
    public void SelectAll() {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        List<TodoDTO> dtolist = session.selectList("com.example.todoseconds.dao.TodoMapper.selectAll");

        dtolist.forEach(todoDTO -> log.info((todoDTO)));

    }


}
