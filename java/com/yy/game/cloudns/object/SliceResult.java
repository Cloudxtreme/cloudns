package com.yy.game.cloudns.object;

import java.util.List;

/**
 * 用于返回集合查询的结果, 其中list存放查询结果, offset是查询结果的偏移, number是查询结果的尺寸, total是查询结果的总数.
 * 
 * @author jason.he
 * @date 2013-11-15
 */
public class SliceResult<T> implements IResult {

	private List<T> data;

	private int offset;

	private int number;

	private int total;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
