package com.yy.game.cloudns.sdk;

import com.yy.game.cloudns.object.IResult.TypeRef;
import com.yy.game.cloudns.object.MessageCodec;

public class HttpUtilsTest {

	public static void main(String[] args) throws Exception {
		System.out.println(MessageCodec.decode("{\"result\":[1,3,4],\"errno\":0}", TypeRef.IntegerArrayResult));
	}

}
