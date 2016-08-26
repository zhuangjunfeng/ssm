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

    public SysUser findUser(Integer yhId) throws Exception {
        //调用mapper类中的selectByExample方法，如果传入类型为null，则表示无条件查找
        SysUser users = userMapper.selectByPrimaryKey(yhId);
        return users;
    }
    public List<SysUser> findAll() throws Exception{
        List<SysUser> users = userMapper.selectAll();
        return users;
    }

}
