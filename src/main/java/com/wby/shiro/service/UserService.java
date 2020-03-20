package com.wby.shiro.service;


import com.wby.shiro.bean.User;

import java.util.Set;

public interface UserService {
     void changePassword(Long userId,String newPassword);
     User findByUsername(String username);
     Set<String> findRoles(String username);
     Set<String> findPermissions(String username);
}
