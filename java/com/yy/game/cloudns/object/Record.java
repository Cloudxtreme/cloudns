package com.yy.game.cloudns.object;

import java.util.Date;

public class Record implements IResult {

	private int id;
	private String name;
	private String type;
	private String zname;
	private String content;
	private int ttl;
	private String isp;
	private int zid;
	private int prio;
	private byte status;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public int getZid() {
		return zid;
	}

	public void setZid(int zid) {
		this.zid = zid;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}

	public Date getCtime() {
		return ctime;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getZname() {
		return zname;
	}

	public void setZname(String zname) {
		this.zname = zname;
	}

	public String toString() {
		return String.format("{name=%s,type=%s,content=%s,ttl=%s,isp=%s,zid=%s,prio=%s}", name, type, content, ttl, isp, zid, prio);
	}

}
