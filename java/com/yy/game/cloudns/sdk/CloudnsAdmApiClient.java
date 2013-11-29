package com.yy.game.cloudns.sdk;

import java.io.IOException;
import java.util.List;

import com.yy.game.cloudns.object.IResult.TypeRef;
import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.MessageCodec;
import com.yy.game.cloudns.object.Record;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.User;
import com.yy.game.cloudns.object.UserLog;
import com.yy.game.cloudns.object.Zone;
import com.yy.game.cloudns.object.ZoneApply;
import com.yy.game.cloudns.object.ZoneApplyHistory;
import com.yy.game.cloudns.security.ICheckCode;

public class CloudnsAdmApiClient extends CloudnsApiClient {

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

	/********************************************************************
	 * USER API方法
	 * 
	 ********************************************************************/
	public Message<String> userEditToken() throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "user_edit_token");
		return readResult(TypeRef.StringResult, "chk", chk, "a", "user_edit_token");
	}

	public Message<SliceResult<UserLog>> userlogLoadAll() throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "userlog_load_all");
		return readSliceResult(TypeRef.UserLogSlice, "chk", chk, "a", "userlog_load_all");
	}

	public Message<SliceResult<UserLog>> userlogLoadAll(int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "userlog_load_all");
		return readSliceResult(TypeRef.UserLogSlice, "chk", chk, "a", "userlog_load_all", "offset", offset, "number", number);
	}

	public Message<SliceResult<ZoneApplyHistory>> applyhistLoadAll(int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "applyhist_load_all");
		return readSliceResult(TypeRef.ZoneApplyHistorySlice, "chk", chk, "a", "applyhist_load_all", "offset", offset, "number", number);
	}

	public Message<SliceResult<Zone>> zoneLoadMulti(int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "zone_load_multi");
		return readSliceResult(TypeRef.ZoneSlice, "chk", chk, "a", "zone_load_multi", "offset", offset, "number", number);
	}

	public Message<String> zoneNew(String z) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "zone_new");
		return readResult(TypeRef.StringResult, "chk", chk, "a", "zone_new", "z", z);
	}

	public Message<SliceResult<Zone>> zoneCheck(String... zones) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "zone_check");
		return readSliceResult(TypeRef.ZoneSlice, "chk", chk, "a", "zone_check", "zones", join(zones));
	}

	public Message<String> zoneDelete(String z) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "zone_delete");
		return readResult(TypeRef.StringResult, "chk", chk, "a", "zone_delete", "z", z);
	}

	public Message<SliceResult<Record>> recLoadAll(String z, int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_load_all");
		return readSliceResult(TypeRef.RecordSlice, "chk", chk, "a", "rec_load_all", "z", z, "offset", offset, "number", number);
	}

	public Message<Integer> recLoadSize(String z) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_load_size");
		return readResult(TypeRef.IntegerResult, "chk", chk, "a", "rec_load_size", "z", z);
	}

	public Message<Record> recLoad(String z, int rid) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_load");
		return readResult(TypeRef.Record, "chk", chk, "a", "rec_load", "z", z, "rid", rid);
	}

	public Message<Record> recNew(String name, String type, String content, Integer ttl, String isp, String z, int prio) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_new");
		return readResult(TypeRef.Record, "chk", chk, "a", "rec_new", "name", name, "type", type, "content", content, "ttl", ttl, "isp", isp, "z", z, "prio", prio);
	}

	public Message<List<Record>> bulkRecNew(String z, Record[] records) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "bulk_rec_new");
		return readResult(TypeRef.RecordList, "chk", chk, "a", "bulk_rec_new", "z", z, "records", MessageCodec.encode(records));
	}

	public Message<Record> recEdit(int rid, String name, String type, String content, Integer ttl, String isp, String z, int prio) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_edit");
		return readResult(TypeRef.Record, "chk", chk, "a", "rec_edit", "rid", rid, "name", name, "type", type, "content", content, "ttl", ttl, "isp", isp, "z", z, "prio", prio);
	}

	public Message<Record> recDelete(String z, Integer rid) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_delete");
		return readResult(TypeRef.Record, "chk", chk, "a", "rec_delete", "z", z, "rid", rid);
	}

	public Message<List<Record>> bulkRecDelete(String z, Integer[] rids) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "bulk_rec_delete");
		return readResult(TypeRef.RecordList, "chk", chk, "a", "bulk_rec_delete", "z", z, "rids", join(rids));
	}

	public Message<SliceResult<Record>> recSearch(String z, String k, int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_search");
		return readSliceResult(TypeRef.RecordSlice, "chk", chk, "a", "rec_search", "z", z, "k", k, "offset", String.valueOf(offset), "number", String.valueOf(number));
	}

	public Message<SliceResult<Record>> recLoadByName(String z, String name) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_load_by_name");
		return readSliceResult(TypeRef.RecordSlice, "chk", chk, "a", "rec_load_by_name", "z", z, "name", name);
	}

	public Message<List<Record>> recDeleteByName(String z, String name) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_delete_by_name");
		return readResult(TypeRef.RecordList, "chk", chk, "a", "rec_delete_by_name", "z", z, "name", name);
	}

	public Message<SliceResult<Record>> recLoadByPrefix(String z, String name, int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "rec_load_by_prefix");
		return readSliceResult(TypeRef.RecordSlice, "chk", chk, "a", "rec_load_by_prefix", "z", z, "name", name, "offset", offset, "number", number);
	}

	/********************************************************************
	 * ADM API方法
	 * 
	 ********************************************************************/
	public Message<SliceResult<ZoneApply>> admZapplyLoadAll(int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_zapply_load_all");
		return readSliceResult(TypeRef.ZoneApplySlice, "chk", chk, "a", "adm_zapply_load_all", "offset", offset, "number", number);
	}

	public Message<Integer> admZapplyApprove(String z, String u, byte stat) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_zapply_approve");
		return readProcessResult("chk", chk, "a", "adm_zapply_approve", "u", u, "z", z, "stat", stat);
	}

	public Message<SliceResult<Zone>> admZoneLoadAll() throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_zone_load_all");
		return readSliceResult(TypeRef.ZoneSlice, "chk", chk, "a", "adm_zone_load_all");
	}

	public Message<SliceResult<Zone>> admZoneLoadAll(int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_zone_load_all");
		return readSliceResult(TypeRef.ZoneSlice, "chk", chk, "a", "adm_zone_load_all", "offset", offset, "number", number);
	}

	public Message<Integer[]> admBulkZoneEntitle(String z, String[] users, byte action) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_bulk_zone_entitle");
		return readResult(TypeRef.IntegerArrayResult, "chk", chk, "a", "adm_bulk_zone_entitle", "users", join(users), "z", z, "action", action);
	}

	public Message<SliceResult<User>> admZuserLoadAll(String z, int offset, int number) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_zuser_load_all");
		return readSliceResult(TypeRef.UserSlice, "chk", chk, "a", "adm_zuser_load_all", "z", z, "offset", offset, "number", number);
	}

	public Message<Integer> admZoneDelete(String z) throws IOException {
		String chk = checkCode.hexCode(psp, tkn, "adm_zone_delete");
		return readResult(TypeRef.IntegerResult, "chk", chk, "a", "adm_zone_delete", "z", z);
	}
}
