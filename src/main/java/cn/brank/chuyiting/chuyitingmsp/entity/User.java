/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019. All rights reserved.
 */

package cn.brank.chuyiting.chuyitingmsp.entity;

/**
 * @author x00504227
 * @version NCE Analyzer R19C00
 * @since 2019-05-28
 */
public class User {
	/**
	 * 
	 */
	private int id;
	private String name;
	private String password;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

}