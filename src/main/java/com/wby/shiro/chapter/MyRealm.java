package com.wby.shiro.chapter;

import com.wby.shiro.realm.Realm;
import org.apache.shiro.authc.*;

public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws ArithmeticException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        if (!"zhang".equals(userName)){
            throw new UnknownAccountException();
        }

        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
