package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.SysUser;

import java.util.List;


public interface SysUserMapper {
    int deleteByPrimaryKey(Integer yhId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer yhId);
    List<SysUser> selectAll();

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}