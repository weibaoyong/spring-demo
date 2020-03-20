package com.wby.shiro.service;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceService {
    Set<String> findPermissions(Set<Long> resourceIds);
    List<Resource> findMenus(Set<String> permissions);
    Object findAll();
}
