package com.yy.game.cloudns.object;

import java.util.Date;

/**
 * 返回User查询信息的载体, 不包含tkn; 其中utype:
 * 
 * 0: 管理用户
 * 
 * 1: 普通用户
 * 
 * 2: 扩展用户
 * 
 * @author jason.he
 * 
 */
public class User implements IResult {

	private int id;
	private String name;
	private byte type;
	private String token;
	private Date ctime;

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

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
