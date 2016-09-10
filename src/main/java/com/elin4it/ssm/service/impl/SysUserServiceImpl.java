package com.elin4it.ssm.service.impl;

import com.elin4it.ssm.mapper.SysUserMapper;
import com.elin4it.ssm.pojo.SysUser;
import com.elin4it.ssm.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper userMapper;

    public SysUserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public SysUser findUserById(Integer yhId) throws Exception {
        SysUser users = userMapper.selectByPrimaryKey(yhId);
        return users;
    }
    public List<SysUser> findAll() throws Exception{
        List<SysUser> users = userMapper.selectAll();
        return users;
    }
    public SysUser findUserByYhzh(String yhzh){
        SysUser users = userMapper.selectByYhzh(yhzh);
        return users;
    }
    public boolean delUserById(Integer yhId){
        if(userMapper.deleteByPrimaryKey(yhId))
            return true;
        else
            return false;
    }
    public boolean addUser(SysUser user){
        if(userMapper.insert(user))
            return true;
        else
            return false;
    }
    public boolean updateUser(SysUser user){
        if(userMapper.updateByPrimaryKey(user))
            return true;
        else
            return false;
    }
}
