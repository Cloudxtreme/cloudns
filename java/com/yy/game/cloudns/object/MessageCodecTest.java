package com.yy.game.cloudns.object;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class MessageCodecTest {

	static ObjectMapper ObjMpr = new ObjectMapper();

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		final String json = "{\"result\":{\"uid\":\"31\",\"psp\":\"dw_hezhaowu\",\"utype\":1,\"tkn\":\"jnl6s81CnUQQwLmbnNo1rAyROZw3CbM6\"},\"errno\":0,\"errmsg\":null}";
		// Message<User> msg = ObjMpr.readValue(json, new TypeReference<Message<User>>() {});
		Message<User> msg = test(json, User.class);
		System.out.println(msg);
	}

	private static <R extends IResult> Message<R> test(String json, Class<R> type) throws IOException {
		return ObjMpr.readValue(json, new TypeReference<Message<R>>() {
		});
	}
}
