package com.yy.game.cloudns.object;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 负责API返回Message的编码与解码.
 * 
 * @author jason.he
 * @date 2013-11-15
 */
public final class MessageCodec {

	static final ObjectMapper ObjMpr = new ObjectMapper();

	private MessageCodec() {
	}

	public static String encode(Object result, int errno, Object[] args) throws IOException {
		Message<Object> msg = new Message<Object>();
		msg.setResult(result);
		msg.setErrno(errno);
		if (errno != 0) {
			String errm = ERRMSGS.get(errno);
			if (args != null && args.length > 0 && errm != null) {
				errm = MessageFormat.format(errm, args);
			}
			msg.setErrmsg(errm);
		}
		return ObjMpr.writeValueAsString(msg);
	}

	public static String encodeSuccess(Object result) throws IOException {
		return encode(result, 0, null);
	}

	public static String encodeError(int errno, Object[] args) throws IOException {
		return encode(null, errno, args);
	}

	public static String encodeUnclassifyError(String errmsg) throws IOException {
		Message<IResult> msg = new Message<IResult>();
		msg.setResult(null);
		msg.setErrno(ERRNOS.UNCLASSIFY);
		msg.setErrmsg(errmsg);
		return ObjMpr.writeValueAsString(msg);
	}

	@SuppressWarnings("unchecked")
	public static <R> R decodeSingleResult(String json, @SuppressWarnings("rawtypes") TypeReference ref) throws IOException {
		return (R)ObjMpr.readValue(json, ref);
	}

	@SuppressWarnings("unchecked")
	public static <R> R decodeSliceResult(String json, @SuppressWarnings("rawtypes") TypeReference ref) throws IOException {
		return (R)ObjMpr.readValue(json, ref);
	}

	@SuppressWarnings("unchecked")
	public static <R> R decodeProcessResult(String json, @SuppressWarnings("rawtypes") TypeReference ref) throws IOException {
		return (R)ObjMpr.readValue(json, ref);
	}

	@SuppressWarnings("unchecked")
	public static <R> R decode(String json, @SuppressWarnings("rawtypes") TypeReference ref) throws IOException {
		return (R)ObjMpr.readValue(json, ref);
	}

	public static <R> R decode(String json, Class<R> type) throws IOException {
		return ObjMpr.readValue(json, type);
	}

	public static String encode(Object value) throws IOException {
		return ObjMpr.writeValueAsString(value);
	}

	public static interface ERRNOS {
		int UNCLASSIFY = 1000;
		int API_IS_NOT_SUPPORTED = 1;
		int HTTP_METHOD_IS_NOT_SUPPORTED = 2;
		int AUTHENTICATE_FAILED = 3;
		int PARA_IS_REQUIRED = 4;
		int PARA_IS_INVALID = 5;
		int PERMISSION_DENIED = 6;
		int DUPLICATE_USER = 7;
		int DUPLICATE_ZONE = 8;
		int PROCESS_FAILED = 9;
		int UNEXPECT_RESULT = 10;
		int USER_IS_INVALID = 11;
		int ZONE_IS_INVALID = 12;
		int REMOTE_VALIDATE_FAILED = 13;
	}

	static final Map<Integer, String> ERRMSGS = new HashMap<Integer, String>();
	static {
		ERRMSGS.put(ERRNOS.API_IS_NOT_SUPPORTED, "api is not supported");
		ERRMSGS.put(ERRNOS.HTTP_METHOD_IS_NOT_SUPPORTED, "http {0} is not supported by the api");
		ERRMSGS.put(ERRNOS.AUTHENTICATE_FAILED, "authenticate failed");
		ERRMSGS.put(ERRNOS.PARA_IS_REQUIRED, "{0} is required");
		ERRMSGS.put(ERRNOS.PARA_IS_INVALID, "{0} is invalid");
		ERRMSGS.put(ERRNOS.PERMISSION_DENIED, "permission denied");
		ERRMSGS.put(ERRNOS.DUPLICATE_USER, "duplicate user");
		ERRMSGS.put(ERRNOS.DUPLICATE_ZONE, "duplicate zone");
		ERRMSGS.put(ERRNOS.PROCESS_FAILED, "process failed: {0}");
		ERRMSGS.put(ERRNOS.UNEXPECT_RESULT, "expect result {0}, but not {1}");
		ERRMSGS.put(ERRNOS.USER_IS_INVALID, "invalid user {0}");
		ERRMSGS.put(ERRNOS.ZONE_IS_INVALID, "invalid zone {0}");
		ERRMSGS.put(ERRNOS.REMOTE_VALIDATE_FAILED, "{0}");
	}
}
