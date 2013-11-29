package com.yy.game.cloudns.object;

import java.util.Date;

/**
 * 
 * 
 * @author jason.he
 * @date 2013-11-15
 */
public class Zone implements IResult {

	private int id;
	private String name;
	private int status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
