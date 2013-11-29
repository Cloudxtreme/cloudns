package com.yy.game.cloudns.object;

public class Message<R> {

	private R result;

	private int errno;

	private String errmsg;

	public R getResult() {
		return result;
	}

	public void setResult(R result) {
		this.result = result;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return String.format("{result:%s,errno:%d,errmsg:%s}", result, errno, errmsg);
	}

}
