package com.wby.shiro.service.impl;

import com.wby.shiro.bean.Permissions;
import com.wby.shiro.bean.Role;
import com.wby.shiro.bean.User;
import com.wby.shiro.service.LoginService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoginServiceImpl implements LoginService {
    @Override
    public User getUserName(String getMapByName) {
        return getMapByName(getMapByName);
    }

    private User getMapByName(String userName) {
        Permissions permissions1 = new Permissions("1","query");
        Permissions permissions2 = new Permissions("2","add");
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);

        Role role = new Role("1","admin",permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        User user = new User("1","1by","123456",roleSet);
        Map<String,User> map = new HashMap<>();
        map.put(user.getUsername(),user);

        Permissions permissions3 = new Permissions("3","query");
        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);

        Role role1 = new Role("2","user",permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        User user1 = new User("2","zpm","123456",roleSet1);
        map.put(user1.getUsername(),user1);
        return map.get(userName);
    }
}
