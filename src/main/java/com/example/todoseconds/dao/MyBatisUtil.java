package com.example.todoseconds.dao;


import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@Getter
public enum MyBatisUtil {

    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    // 생성자 생성
    // JavaBeans는 기본 생성자를 생성해서 만들어야 한다.
    MyBatisUtil() {

        try {

            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
