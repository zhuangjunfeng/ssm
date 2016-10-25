package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.SysUser;

import java.util.List;

/**
 * 内容管理平台用户模块持久层
 * Created by Administrator on 2016/8/31.
 */
public interface SysUserMapper {
    /**
     * 根据新闻ID删除用户
     * @param yhId 用户ID
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(Integer yhId);

    /**
     * 添加用户
     * @param record 添加用户的信息
     * @return 是否成功
     */
    boolean insert(SysUser record);

    int insertSelective(SysUser record);

    /**
     * 根据用户ID查询用户
     * @param yhId 用户ID
     * @return 目标用户信息
     */
    SysUser selectByPrimaryKey(Integer yhId);

    /**
     * 查找全部用户
     * @return 用户列表
     */
    List<SysUser> selectAll();

    /**
     * 根据用户账户查询用户
     * @param yhzh 用户账户
     * @return 对应用户信息
     */
    SysUser selectByYhzh(String yhzh);

    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 更新用户
     * @param record 更新用户的信息
     * @return 是否成功
     */
    boolean updateByPrimaryKey(SysUser record);
}