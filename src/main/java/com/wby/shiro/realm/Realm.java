package com.wby.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface Realm {

    String getName();
    boolean supports(AuthenticationToken token);
    AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws ArithmeticException;
}
