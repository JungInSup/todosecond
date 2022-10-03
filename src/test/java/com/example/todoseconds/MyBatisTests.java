package com.example.todoseconds;

import com.example.todoseconds.dao.MyBatisUtil;
import com.example.todoseconds.dto.TodoDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class MyBatisTests {

    @Test
    public void update() {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        session.update("com.example.todoseconds.dao.TodoMapper.update");
    }

    @Test
    public void delete() {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        session.delete("com.example.todoseconds.dao.TodoMapper.delete");
    }

    @Test
    public void insert() {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        session.insert("com.example.todoseconds.dao.TodoMapper.insert");
    }

    @Test
    public void SelectAll() {

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        List<TodoDTO> dtolist = session.selectList("com.example.todoseconds.dao.TodoMapper.selectAll");

        dtolist.forEach(todoDTO -> log.info((todoDTO)));

    }

    @Test
    public void testTime() {

        log.info(".........testTime");

        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        log.info("session =======================================");
        log.info(session);

        String timeStr = session.selectOne("com.example.todoseconds.dao.TimeMapper.getTime");

        log.info("timeStr =======================================");
        log.info(timeStr);

        session.close();

    }
}
