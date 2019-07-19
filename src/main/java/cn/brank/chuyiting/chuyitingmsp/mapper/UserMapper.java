/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019. All rights reserved.
 */

package cn.brank.chuyiting.chuyitingmsp.mapper;

import cn.brank.chuyiting.chuyitingmsp.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author x00504227
 * @version NCE Analyzer R19C00
 * @since 2019-05-28
 */
@Mapper
public interface UserMapper {
	
	@Insert("insert into user(name, password) values(#{name},#{password})")
	public int save(User user);

	@Insert("delete from user where name=#{name} and password=#{password}")
	public int delete(User user);

	@Select("select count(*) from user where name=#{name}")
	public int exits(@Param("name") String name);

	@Select("select * from user where name=#{name} and password=#{password}")
	public User get(@Param("name") String name, @Param("password") String password);

	@Update("update user set password=#{newPassWord} where name=#{name} and password=#{oldPassWord}")
	public int update(@Param("name") String name, @Param("oldPassWord") String oldPassWord, @Param("newPassWord") String newPassWord);
}
