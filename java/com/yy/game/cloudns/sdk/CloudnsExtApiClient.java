package com.yy.game.cloudns.sdk;

import java.io.IOException;

import com.yy.game.cloudns.object.IResult.TypeRef;
import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.User;
import com.yy.game.cloudns.security.ICheckCode;

public class CloudnsExtApiClient extends AbstractCloudnsApiClient {

	public static final int UTYPE_USER = 1;
	public static final int UTYPE_ADM = 0;
	public static final int UTYPE_EXT = 2;

	private ICheckCode checkCode;

	public ICheckCode getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(ICheckCode checkCode) {
		this.checkCode = checkCode;
	}

	public final Message<User> extUserLoad(String uname) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "ext_user_load");
		return readSingleResult(TypeRef.User, "chk", chk, "a", "ext_user_load", "u", uname);
	}

	public final Message<Integer> extUserNew(String uname, int utype) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "ext_user_new");
		return readProcessResult("chk", chk, "a", "ext_user_new", "u", uname, "utype", String.valueOf(utype));
	}

	public final Message<SliceResult<User>> extUserLoadAll() throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "ext_user_load_all");
		return readSliceResult(TypeRef.UserSlice, "chk", chk, "a", "ext_user_load_all");
	}
}
