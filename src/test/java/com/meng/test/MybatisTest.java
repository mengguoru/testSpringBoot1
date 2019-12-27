package com.meng.test;

import com.meng.dao.IUserDao;
import com.meng.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:SqlMapConfig.xml"})
@SpringBootTest
public class MybatisTest {

    InputStream in;
    SqlSession session;
    IUserDao userDao;

    @Before
    public void init() throws  Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();

        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void after() throws IOException {
        session.commit();

        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for(User user:users)
            System.out.println(user);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("小王");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("广州");

        userDao.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(24);
        user.setUsername("小王");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("广州");

        userDao.update(user);
    }

    @Test
    public void testDelete(){
        userDao.delete(25);
    }

    @Test
    public void testFindById(){
        System.out.println(userDao.findById(20));
    }

    @Test
    public void testFindByIds(){
        List<Integer> list = Lists.newArrayList(21,22);
        List<User> users = userDao.findByIds(list);
        for(User user:users)
            System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> list = userDao.findByName("%小%");
        for(User user:list)
            System.out.println(user);
    }

    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setId(21);
        List<User> list = userDao.findByCondition(user);
        for(User u:list)
            System.out.println(u);
        System.out.println("---分割线---");
        user = new User();
        user.setUsername("小红");
        user.setSex("女");
        list = userDao.findByCondition(user);
        for(User u:list)
            System.out.println(u);
    }

    @Test
    public void testTotal(){
        System.out.println(userDao.total());
    }
}
