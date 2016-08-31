package com.elin4it.ssm.service;

import com.elin4it.ssm.pojo.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
@Service
public interface SysUserService {
    SysUser findUserByYhzh(String yhzh) ;
    SysUser findUserById(Integer yhId)throws Exception;
    List<SysUser> findAll() throws Exception;
    boolean delUserById(Integer yhid);
    boolean addUser(SysUser user);
    boolean updateUser(SysUser user);
}
