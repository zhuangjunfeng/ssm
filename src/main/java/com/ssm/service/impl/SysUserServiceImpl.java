package com.ssm.service.impl;

import com.ssm.mapper.SysUserMapper;
import com.ssm.pojo.SysUser;
import com.ssm.service.SysUserService;
import com.ssm.mapper.SysUserMapper;
import com.ssm.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容管理平台用户模块业务层实现类
 * Created by Administrator on 2016/8/31.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper userMapper;

    /**
     * 根据用户ID查询用户
     *
     * @param yhId 用户ID
     * @return 目标用户信息
     * @throws Exception
     */
    public SysUser findUserById(Integer yhId) throws Exception {
        SysUser users = userMapper.selectByPrimaryKey(yhId);
        return users;
    }

    /**
     * 查询全部用户
     *
     * @return 全部用户列表
     * @throws Exception
     */
    public List<SysUser> findAll() throws Exception {
        List<SysUser> users = userMapper.selectAll();
        return users;
    }

    /**
     * 根据用户账户查询用户
     *
     * @param yhzh 用户账户
     * @return 对应用户信息
     */
    public SysUser findUserByYhzh(String yhzh) {
        SysUser users = userMapper.selectByYhzh(yhzh);
        return users;
    }

    /**
     * 根据用户ID删除用户
     *
     * @param yhId
     * @return 是否成功
     */
    public boolean delUserById(Integer yhId) {
        if (userMapper.deleteByPrimaryKey(yhId))
            return true;
        else
            return false;
    }

    /**
     * 添加用户
     *
     * @param user 添加用户的信息
     * @return 是否成功
     */
    public boolean addUser(SysUser user) {
        if (userMapper.insert(user))
            return true;
        else
            return false;
    }

    /**
     * 更新用户
     *
     * @param user 更新的用户的信息
     * @return 是否成功
     */
    public boolean updateUser(SysUser user) {
        if (userMapper.updateByPrimaryKeySelective(user))
            return true;
        else
            return false;
    }

    /**
     * 分页查询所有用户
     * @param PageNo 页码
     * @param PageSize 每页记录条数
     * @return
     */
    public List<SysUser> findUserByPageNo(int PageNo, int PageSize) {
        int start = 0;
        start = (PageNo - 1) * PageSize;
        return userMapper.selectUser(start, PageSize);
    }

    /**
     * 查询用户总数
     * @return 用户总数
     */
    public String count() {
        return userMapper.count();
    }
}
