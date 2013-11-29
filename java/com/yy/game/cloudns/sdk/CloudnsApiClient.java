package com.yy.game.cloudns.sdk;

import java.io.IOException;
import java.util.List;

import com.yy.game.cloudns.object.IResult.TypeRef;
import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.MessageCodec;
import com.yy.game.cloudns.object.Record;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.UserLog;
import com.yy.game.cloudns.object.Zone;
import com.yy.game.cloudns.object.ZoneApplyHistory;

public class CloudnsApiClient extends AbstractCloudnsApiClient {

	/********************************************************************
	 * USER API方法
	 * 
	 ********************************************************************/
	public Message<String> userEditToken() throws IOException {
		return readResult(TypeRef.StringResult, "a", "user_edit_token");
	}

	public Message<SliceResult<UserLog>> userlogLoadAll() throws IOException {
		return readSliceResult(TypeRef.UserLogSlice, "a", "userlog_load_all");
	}

	public Message<SliceResult<UserLog>> userlogLoadAll(int offset, int number) throws IOException {
		return readSliceResult(TypeRef.UserLogSlice, "a", "userlog_load_all", "offset", offset, "number", number);
	}

	public Message<SliceResult<ZoneApplyHistory>> applyhistLoadAll(int offset, int number) throws IOException {
		return readSliceResult(TypeRef.ZoneApplyHistorySlice, "a", "applyhist_load_all", "offset", offset, "number", number);
	}

	public Message<SliceResult<Zone>> zoneLoadMulti(int offset, int number) throws IOException {
		return readSliceResult(TypeRef.ZoneSlice, "a", "zone_load_multi", "offset", offset, "number", number);
	}

	public Message<String> zoneNew(String z) throws IOException {
		return readResult(TypeRef.StringResult, "a", "zone_new", "z", z);
	}

	public Message<SliceResult<Zone>> zoneCheck(String... zones) throws IOException {
		return readSliceResult(TypeRef.ZoneSlice, "a", "zone_check", "zones", join(zones));
	}

	public Message<String> zoneDelete(String z) throws IOException {
		return readResult(TypeRef.StringResult, "a", "zone_delete", "z", z);
	}

	public Message<SliceResult<Record>> recLoadAll(String z, int offset, int number) throws IOException {
		return readSliceResult(TypeRef.RecordSlice, "a", "rec_load_all", "z", z, "offset", offset, "number", number);
	}

	public Message<Integer> recLoadSize(String z) throws IOException {
		return readResult(TypeRef.IntegerResult, "a", "rec_load_size", "z", z);
	}

	public Message<Record> recLoad(String z, int rid) throws IOException {
		return readResult(TypeRef.Record, "a", "rec_load", "z", z, "rid", rid);
	}

	public Message<Record> recNew(String name, String type, String content, Integer ttl, String isp, String z, int prio) throws IOException {
		return readResult(TypeRef.Record, "a", "rec_new", "name", name, "type", type, "content", content, "ttl", ttl, "isp", isp, "z", z, "prio", prio);
	}

	public Message<List<Record>> bulkRecNew(String z, Record[] records) throws IOException {
		return readResult(TypeRef.RecordList, "a", "bulk_rec_new", "z", z, "records", MessageCodec.encode(records));
	}

	public Message<Record> recEdit(int rid, String name, String type, String content, Integer ttl, String isp, String z, int prio) throws IOException {
		return readResult(TypeRef.Record, "a", "rec_edit", "rid", rid, "name", name, "type", type, "content", content, "ttl", ttl, "isp", isp, "z", z, "prio", prio);
	}

	public Message<Record> recDelete(String z, Integer rid) throws IOException {
		return readResult(TypeRef.Record, "a", "rec_delete", "z", z, "rid", rid);
	}

	public Message<List<Record>> bulkRecDelete(String z, Integer[] rids) throws IOException {
		return readResult(TypeRef.RecordList, "a", "bulk_rec_delete", "z", z, "rids", join(rids));
	}

	public Message<SliceResult<Record>> recSearch(String z, String k, int offset, int number) throws IOException {
		return readSliceResult(TypeRef.RecordSlice, "a", "rec_search", "z", z, "k", k, "offset", String.valueOf(offset), "number", String.valueOf(number));
	}

	public Message<SliceResult<Record>> recLoadByName(String z, String name) throws IOException {
		return readSliceResult(TypeRef.RecordSlice, "a", "rec_load_by_name", "z", z, "name", name);
	}

	public Message<List<Record>> recDeleteByName(String z, String name) throws IOException {
		return readResult(TypeRef.RecordList, "a", "rec_delete_by_name", "z", z, "name", name);
	}

	public Message<SliceResult<Record>> recLoadByPrefix(String z, String name, int offset, int number) throws IOException {
		return readSliceResult(TypeRef.RecordSlice, "a", "rec_load_by_prefix", "z", z, "name", name, "offset", offset, "number", number);
	}

}
