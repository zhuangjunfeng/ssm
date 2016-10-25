package com.elin4it.ssm.service;

import com.elin4it.ssm.pojo.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理平带用户模块业务层接口
 * Created by Administrator on 2016/8/25.
 */
@Service
public interface SysUserService {
    /**
     * 根据用户账户查询用户
     * @param yhzh 用户账户
     * @return 对应用户信息
     */
    SysUser findUserByYhzh(String yhzh) ;

    /**
     * 根据用户ID查询用户
     * @param yhId 用户ID
     * @return 对应用户信息
     * @throws Exception
     */
    SysUser findUserById(Integer yhId)throws Exception;

    /**
     * 查询全部用户
     * @return 用户列表
     * @throws Exception
     */
    List<SysUser> findAll() throws Exception;

    /**
     * 根据用户ID删除用户
     * @param yhid 用户ID
     * @return 是否成功
     */
    boolean delUserById(Integer yhid);

    /**
     * 添加用户
     * @param user 添加用户的信息
     * @return 是否成功
     */
    boolean addUser(SysUser user);

    /**
     * 更新用户
     * @param user 更新的用户的信息
     * @return 是否成功
     */
    boolean updateUser(SysUser user);
}
