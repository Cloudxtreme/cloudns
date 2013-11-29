package com.yy.game.cloudns.sdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;

import com.yy.game.cloudns.object.IResult;
import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.MessageCodec;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.IResult.TypeRef;

public class AbstractCloudnsApiClient {
	
	private final Map<String, Object> hookParams = new HashMap<String, Object>();

	protected String psp;
	protected String tkn;
	protected String apiUrl;

	public final String getTkn() {
		return tkn;
	}

	public final void setTkn(String tkn) {
		this.tkn = tkn;
		paramHook("tkn", tkn);
	}

	public final String getPsp() {
		return psp;
	}

	public final void setPsp(String psp) {
		this.psp = psp;
		paramHook("psp", psp);
	}

	public final String getApiUrl() {
		return apiUrl;
	}

	public final void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	protected final <T extends IResult> Message<T> readSingleResult(TypeReference<Message<T>> ref, Object... paramList) throws IOException {
		String json = HttpUtils.readJsonMV(apiUrl, hookParams, paramList);
		Message<T> msg = MessageCodec.decodeSingleResult(json, ref);
		return msg;
	}

	protected final <T extends IResult> Message<SliceResult<T>> readSliceResult(TypeReference<Message<SliceResult<T>>> ref, Object... paramList) throws IOException {
		String json = HttpUtils.readJsonMV(apiUrl, hookParams, paramList);
		Message<SliceResult<T>> msg = MessageCodec.decodeSliceResult(json, ref);
		return msg;
	}

	protected final Message<Integer> readProcessResult(Object... paramList) throws IOException {
		String json = HttpUtils.readJsonMV(apiUrl, hookParams, paramList);
		Message<Integer> msg = MessageCodec.decodeSingleResult(json, TypeRef.IntegerResult);
		return msg;
	}

	protected final <R> Message<R> readResult(TypeReference<Message<R>> ref, Object... paramList) throws IOException {
		String json = HttpUtils.readJsonMV(apiUrl, hookParams, paramList);
		Message<R> msg = MessageCodec.decode(json, ref);
		return msg;
	}

	protected final <R> Message<R> readResult(Class<Message<R>> type, Object... paramList) throws IOException {
		String json = HttpUtils.readJsonMV(apiUrl, hookParams, paramList);
		Message<R> msg = (Message<R>) MessageCodec.decode(json, type);
		return msg;
	}

	protected final void paramHook(String key, Object val) {
		hookParams.put(key, val);
	}

	protected static String join(Object[] zones) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < zones.length; i++) {
			if (i > 0) {
				sb.append(',');
			}
			sb.append(zones[i]);
		}
		return sb.toString();
	}
}
