package com.yy.game.cloudns.sdk;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.Record;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.UserLog;
import com.yy.game.cloudns.object.Zone;
import com.yy.game.cloudns.object.ZoneApply;
import com.yy.game.cloudns.object.ZoneApplyHistory;

public class UserApiTest {

	CloudnsApiClient client;

	@Before
	public void before() {
		client = new CloudnsApiClient();
		client.setApiUrl("http://localhost/v1.2/api/");
		client.setPsp("dw_hezhaowu1");
		client.setTkn("vSp8GfJ5sVXEqncRlc8rYm5tV1lELN5i");
	}

	@Test
	public void userEditToken() throws IOException {
		System.out.println("userEditToken");
		Message<String> msg = client.userEditToken();
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	@Test
	public void userlogLoadAll() throws IOException {
		System.out.println("userlogLoadAll");
		Message<SliceResult<UserLog>> msg = client.userlogLoadAll();
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<UserLog> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatUserLog(data.get(i)));
				}
			}
		}
	}

	@Test
	public void applyhistLoadAll() throws IOException {
		System.out.println("applyhistLoadAll");
		Message<SliceResult<ZoneApplyHistory>> msg = client.applyhistLoadAll(0, -1);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<ZoneApplyHistory> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatZoneApplyHistory(data.get(i)));
				}
			}
		}
	}

	@Test
	public void zoneLoadMulti() throws IOException {
		System.out.println("zoneLoadMulti");
		Message<SliceResult<Zone>> msg = client.zoneLoadMulti(0, -1);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<Zone> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatZone(data.get(i)));
				}
			}
		}
	}

	@Test
	public void zoneNew() throws IOException {
		System.out.println("zoneNew");
		Message<String> msg = client.zoneNew("xxxx.com");
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	@Test
	public void zoneDelete() throws IOException {
		System.out.println("zoneDelete");
		Message<String> msg = client.zoneDelete("xxxx.com");
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	@Test
	public void recLoadAll() throws IOException {
		System.out.println("recLoadAll");
		Message<SliceResult<Record>> msg = client.recLoadAll("xxxx.com", 0, -1);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	@Test
	public void recLoadSize() throws IOException {
		System.out.println("recLoadSize");
		Message<Integer> msg = client.recLoadSize("duowandev.com");
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	@Test
	public void recNew() throws IOException {
		System.out.println("recNew");
		String name = "rise2";
		String type = "MX";
		String content = "www.mx.com";
		int ttl = 400;
		String isp = "tel";
		String z = "xxxx.com";
		int prio = 3;
		Message<Record> msg = client.recNew(name, type, content, ttl, isp, z, prio);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(formatRecord(msg.getResult()));
		}
	}

	@Test
	public void recLoad() throws IOException {
		System.out.println("recLoad");
		Message<Record> msg = client.recLoad("xxxx.com", 2);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(formatRecord(msg.getResult()));
		}
	}

	@Test
	public void bulkRecNew() throws IOException {
		System.out.println("bulkRecNew");
		String name = "bulk";
		String type = "A";
		String content = "127.0.0.1";
		int ttl = 300;
		String isp = "tel";
		String z = "xxxx.com";
		int prio = 0;

		Record[] records = new Record[3];
		for (int i = 0; i < 3; i++) {
			Record rec = new Record();
			rec.setName(name + i);
			rec.setType(type);
			rec.setContent(content);
			rec.setTtl(ttl);
			rec.setIsp(isp);
			rec.setPrio(prio);

			records[i] = rec;
		}
		Message<List<Record>> msg = client.bulkRecNew(z, records);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	@Test
	public void recEdit() throws IOException {
		System.out.println("recEdit");
		int rid = 2;
		String name = "rise3";
		String type = "TXT";
		String content = "{127.3.3.1}";
		int ttl = 300;
		String isp = "tel";
		String z = "xxxx.com";
		int prio = 0;
		Message<Record> msg = client.recEdit(rid, name, type, content, ttl, isp, z, prio);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(formatRecord(msg.getResult()));
		}
	}

	@Test
	public void recDelete() throws IOException {
		System.out.println("recDelete");
		int rid = 2;
		String z = "xxxx.com";
		Message<Record> msg = client.recDelete(z, rid);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			System.out.println(formatRecord(msg.getResult()));
		}
	}

	@Test
	public void bulkRecDelete() throws IOException {
		System.out.println("bulkRecDelete");
		Integer[] rids = new Integer[] { 3, 4 };
		String z = "xxxx.com";
		Message<List<Record>> msg = client.bulkRecDelete(z, rids);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	@Test
	public void recSearch() throws IOException {
		System.out.println("recSearch");
		Message<SliceResult<Record>> msg = client.recSearch("xxxx.com", "bulk", 0, -1);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	@Test
	public void recLoadByName() throws IOException {
		System.out.println("recLoadByName");
		Message<SliceResult<Record>> msg = client.recLoadByName("xxxx.com", "bulk1");
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	@Test
	public void recDeleteByName() throws IOException {
		System.out.println("recDeleteByName");
		String z = "xxxx.com";
		String name = "bulk1";
		Message<List<Record>> msg = client.recDeleteByName(z, name);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	@Test
	public void recLoadByPrefix() throws IOException {
		System.out.println("recLoadByPrefix");
		Message<SliceResult<Record>> msg = client.recLoadByPrefix("xxxx.com", "bu*k*", 0, -1);
		if (msg.getErrno() != 0) {
			System.out.println(msg.getErrmsg());
		} else {
			List<Record> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatRecord(data.get(i)));
				}
			}
		}
	}

	// ///////////////////////////////////////////////////////
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
		if (record == null) {
			return null;
		}
		return String.format("{id=%s,name=%s,type=%s,content=%s,ttl=%s,isp=%s,zid=%s,prio=%s,status=%s}", record.getId(), record.getName(), record.getType(), record.getContent(), record.getTtl(), record.getIsp(), record.getZid(), record.getPrio(), record.getStatus());
	}

}
