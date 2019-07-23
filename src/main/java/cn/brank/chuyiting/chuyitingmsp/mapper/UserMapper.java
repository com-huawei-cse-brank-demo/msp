/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019. All rights reserved.
 */

package cn.brank.chuyiting.chuyitingmsp.mapper;

import cn.brank.chuyiting.chuyitingmsp.entity.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
	
	@Insert("insert into user(user_name, user_password) values(#{userName},#{userPassword})")
	@Options(useGeneratedKeys = true,keyProperty = "userId", keyColumn = "user_id")
	void save(User user);

	@Insert("delete from user where user_name=#{userName} and user_password=#{userPassword}")
	public int delete(User user);

	@Select("select count(*) from user where user_name=#{user_name}")
	public int exits(@Param("user_name") String user_name);

	@Select("select * from user where user_name=#{user_name} and user_password=#{user_password}")
	public User get(@Param("user_name") String user_name, @Param("user_password") String user_password);

	@Update("update user set user_password=#{newPassWord} where user_name=#{user_name} and user_password=#{oldPassWord}")
	public int update(@Param("user_name") String user_name, @Param("oldPassWord") String oldPassWord, @Param("newPassWord") String newPassWord);
}
