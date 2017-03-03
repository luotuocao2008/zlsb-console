package com.zonli.zslb.service;

import com.zonli.zslb.common.entity.User;
import com.zonli.zslb.common.model.Page;
import com.zonli.zslb.util.SecurityUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-mybatis.xml"})
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Before
    public void before() {
    }

    @Test
    public void valid() {
        User user = userService.getByUsernameAndPassword("admin", SecurityUtil.MD5("admin"));
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserByPage() {
        Page page = new Page();
        page.setPageSize(2);
        page.setCurrentPage(3);
        List<User> list = userService.getByPage(page);
        System.out.print("username(0):" + list.get(0).getUsername() + " size:" + list.size());
        System.out.print(" pageNumber:" + page.getPageNumber());
        System.out.println();
    }

    @Test
    public void get() {
        User user = userService.get(1);
        Assert.assertNotNull(user);
    }

    @Test
    public void getByUsername(){
        User user = userService.getByUsername("admin");
        Assert.assertNotNull(user);
    }

    @Ignore
    @Test
    public void insert() {
    }

    @Test
    public void updateDiscard(){
    }
}
