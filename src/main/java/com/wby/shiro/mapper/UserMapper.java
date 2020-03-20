package com.wby.shiro.mapper;

import com.wby.shiro.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);
}
