package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.SysUser;

import java.util.List;

public interface SysUserMapper {
    boolean deleteByPrimaryKey(Integer yhId);

    boolean insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer yhId);
    List<SysUser> selectAll();
    SysUser selectByYhzh(String yhzh);

    int updateByPrimaryKeySelective(SysUser record);

    boolean updateByPrimaryKey(SysUser record);
}