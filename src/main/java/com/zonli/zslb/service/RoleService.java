package com.zonli.zslb.service;

import com.zonli.zslb.dao.RoleMapper;
import com.zonli.zslb.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAll(){
        return roleMapper.selectAll();
    }
}
