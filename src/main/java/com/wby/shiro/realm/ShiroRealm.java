package com.wby.shiro.realm;


import com.wby.shiro.bean.User;
import com.wby.shiro.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    /**获取用户角色和权限*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**登陆认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的用户和密码
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        System.out.println("用户"+userName+"认证-----ShiroRealm.doGetAuthenticationInfo");

        //通过用户名查询数据库用户信息
        User user = userMapper.findByUserName(userName);

        if (null == user){
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())){
            throw  new IncorrectCredentialsException("用户名或密码错误！");
        }
        if ("0".equals(user.getStatus())){
            throw new LockedAccountException("账号已被锁定，请联系管理员！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
        return info;
    }
}
