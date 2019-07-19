/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019. All rights reserved.
 */

package cn.brank.chuyiting.chuyitingmsp.service;


import cn.brank.chuyiting.chuyitingmsp.entity.User;
import cn.brank.chuyiting.chuyitingmsp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author brankLY
 * @version NCE Analyzer R19C00
 * @since 2019-07-18
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int save(User user) {
	    int i = this.exits(user.getName());
        if (i != 0) {
            return 0;
        }
		return userMapper.save(user);
	}
	public int delete(User user) {
	    int i = this.exits(user.getName());
	    if (i == 0){
	        return 0;
        }
        if (userMapper.delete(user) == 0){
            return -1;
        }
        return 1;
    }

	public int exits(String name) {return userMapper.exits(name);}
	
	public User get(String name, String password) {
		return userMapper.get(name, password);
	}

	public int update(String name, String oldPassWord, String newPassWord){
        int i = this.exits(name);
        if (i == 0){
            return 0;
        }
        if (userMapper.update(name, oldPassWord, newPassWord ) == 0){
            return -1;
        }
        return 1;
	}
}
