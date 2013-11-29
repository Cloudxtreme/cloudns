package com.yy.game.cloudns.object;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

public interface IResult {

	enum ResultType {

		SingleResult,
		SliceResult,
		IterSliceResult,
		ProcessResult;

	}

	interface TypeRef {
		TypeReference<Message<Record>> Record = new TypeReference<Message<Record>>() {
		};
		TypeReference<Message<SliceResult<Record>>> RecordSlice = new TypeReference<Message<SliceResult<Record>>>() {
		};
		TypeReference<Message<List<Record>>> RecordList = new TypeReference<Message<List<Record>>>() {
		};

		TypeReference<Message<User>> User = new TypeReference<Message<User>>() {
		};
		TypeReference<Message<SliceResult<User>>> UserSlice = new TypeReference<Message<SliceResult<User>>>() {
		};

		TypeReference<Message<UserLog>> UserLog = new TypeReference<Message<UserLog>>() {
		};
		TypeReference<Message<SliceResult<UserLog>>> UserLogSlice = new TypeReference<Message<SliceResult<UserLog>>>() {
		};

		TypeReference<Message<Zone>> Zone = new TypeReference<Message<Zone>>() {
		};
		TypeReference<Message<SliceResult<Zone>>> ZoneSlice = new TypeReference<Message<SliceResult<Zone>>>() {
		};

		TypeReference<Message<ZoneApply>> ZoneApply = new TypeReference<Message<ZoneApply>>() {
		};
		TypeReference<Message<SliceResult<ZoneApply>>> ZoneApplySlice = new TypeReference<Message<SliceResult<ZoneApply>>>() {
		};

		TypeReference<Message<ZoneApplyHistory>> ZoneApplyHistory = new TypeReference<Message<ZoneApplyHistory>>() {
		};
		TypeReference<Message<SliceResult<ZoneApplyHistory>>> ZoneApplyHistorySlice = new TypeReference<Message<SliceResult<ZoneApplyHistory>>>() {
		};

		TypeReference<Message<Integer>> IntegerResult = new TypeReference<Message<Integer>>() {
		};

		TypeReference<Message<String>> StringResult = new TypeReference<Message<String>>() {
		};

		TypeReference<Message<Integer[]>> IntegerArrayResult = new TypeReference<Message<Integer[]>>() {
		};

	}
}
