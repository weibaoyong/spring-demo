package com.wby.shiro.service;

import java.util.Set;

public interface RoleService {
    Set<String> findRoles(Long...roleIds);
    Set<String> findPermissions(Long[] roleIds);
}
