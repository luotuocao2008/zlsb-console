package com.zonli.zslb.service;

import com.zonli.zslb.common.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-mybatis.xml"})
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Before
    public void setup(){
    }

    @Test
    public void getAll(){
        List<Role> list = roleService.getAll();
        System.out.println(list.size());
    }
}
