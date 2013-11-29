package com.yy.game.cloudns.sdk;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yy.game.cloudns.object.CloudnsApiConstants;
import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.Record;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.User;
import com.yy.game.cloudns.object.UserLog;
import com.yy.game.cloudns.object.Zone;
import com.yy.game.cloudns.object.ZoneApply;
import com.yy.game.cloudns.object.ZoneApplyHistory;
import com.yy.game.cloudns.security.CheckCodeGenerator;

public class AdmApiTest {

	CloudnsAdmApiClient client;

	@Before
	public void before() {
		client = new CloudnsAdmApiClient();
		client.setApiUrl("http://localhost/v1.2/api/");
		client.setPsp("dw_hezhaowu0");
		client.setTkn("LHCuMIGzqylJN6GAe76Q7vRU0mult6e2");
		client.setCheckCode(CheckCodeGenerator.getInstance());
	}

	@Test
	public void admZapplyLoadAll() throws IOException {
		System.out.println("admZapplyLoadAll");
		Message<SliceResult<ZoneApply>> msg = client.admZapplyLoadAll(0, -1);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<ZoneApply> data = msg.getResult().getData();
			for (int i = 0; i < data.size(); i++) {
				System.out.println(i + "=>" + formatZoneApply(data.get(i)));
			}
		}
	}

	@Test
	public void admZapplyApprove() throws IOException {
		System.out.println("admZapplyApprove");
		Message<Integer> msg = client.admZapplyApprove("xxxx.com", "dw_hezhaowu1", CloudnsApiConstants.CZoneApply.STATUS_PASS);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	@Test
	public void admZoneLoadAll() throws IOException {
		System.out.println("admZoneLoadAll");
		Message<SliceResult<Zone>> msg = client.admZoneLoadAll();
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<Zone> data = msg.getResult().getData();
			for (int i = 0; i < data.size(); i++) {
				System.out.println(i + "=>" + formatZone(data.get(i)));
			}
		}
	}

	@Test
	public void admBulkZoneEntitle() throws IOException {
		System.out.println("admBulkZoneEntitle");
		Message<Integer[]> msg = client.admBulkZoneEntitle("xxxx.com", new String[] { "dw_hezhaowu1", "dw_hezhaowu0" }, CloudnsApiConstants.CUserZone.ACTION_REQ_ADD);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			System.out.println(Arrays.toString(msg.getResult()));
		}
	}

	@Test
	public void admZuserLoadAll() throws IOException {
		System.out.println("admZuserLoadAll");
		Message<SliceResult<User>> msg = client.admZuserLoadAll("xxxx.com", 0, -1);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<User> data = msg.getResult().getData();
			for (int i = 0; i < data.size(); i++) {
				System.out.println(i + "=>" + formatUser(data.get(i)));
			}
		}
	}

	@Test
	public void admZoneDelete() throws IOException {
		System.out.println("admZuserLoadAll");
		Message<Integer> msg = client.admZoneDelete("xxxx.com");
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	// //////////////////////////////////////////////////////////////////
	protected String formatUserLog(UserLog userlog) {
		return String.format("{uid=%s,logType=%s,content=%s,ctime=%s}", userlog.getUid(), userlog.getLogType(), userlog.getContent(), userlog.getOperateTime());
	}

	protected String formatZoneApplyHistory(ZoneApplyHistory zah) {
		return String.format("{aid=%s,uname=%s,zname=%s,action=%s,status=%s,ctime=%s,lastUpdateTime=%s}", zah.getAid(), zah.getUname(), zah.getZname(), zah.getAction(), zah.getStatus(), zah.getCtime(), zah.getLastuptime());
	}

	protected String formatZone(Zone zone) {
		return String.format("{id=%s,name=%s,status=%s,ctime=%s}", zone.getId(), zone.getName(), zone.getStatus(), zone.getCtime());
	}

	protected String formatZoneApply(ZoneApply za) {
		return String.format("{id=%s,uname=%s,zname=%s,status=%s,action=%s}", za.getId(), za.getUname(), za.getZname(), za.getStatus(), za.getAction());
	}

	protected String formatRecord(Record record) {
		return String.format("{id=%s,name=%s,type=%s,content=%s,ttl=%s,isp=%s,zid=%s,prio=%s,status=%s}", record.getId(), record.getName(), record.getType(), record.getTtl(), record.getIsp(), record.getStatus());
	}

	protected String formatUser(User user) {
		return String.format("{id=%s,name=%s,type=%s}", user.getId(), user.getName(), user.getType());
	}

}
