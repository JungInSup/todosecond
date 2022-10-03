package com.example.todoseconds.dao;


import com.example.todoseconds.dto.TodoDTO;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public enum TodoDAO {

    INSTANCE;

    public List<TodoDTO> SelectAll() {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        List<TodoDTO> dtolist = session.selectList("com.example.todoseconds.dao.TodoMapper.selectAll");

        return dtolist;

    }

    public TodoDTO Select(String tno) {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        TodoDTO dto = session.selectOne("com.example.todoseconds.dao.TodoMapper.select", tno);

        return dto;
    }

    public void Delete(String tno) {


        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        session.selectOne("com.example.todoseconds.dao.TodoMapper.delete", tno);

    }
}