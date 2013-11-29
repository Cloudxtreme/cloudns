package com.yy.game.cloudns.object;

import java.util.Arrays;
import java.util.List;

public interface CloudnsApiConstants {

	String ITEM_SEPARATOR = "\\s*,\\s*";

	String METHOD_GET = "GET";
	String METHOD_POST = "POST";

	int DEFVAL_NUMBER = 100;

	interface CUser {
		byte UTYPE_USER = 1;
		byte UTYPE_ADM = 0;
		byte UTYPE_EXT = 2;
	}

	interface CZoneApply {
		/** 表示请求添加 **/
		byte ACTION_REQ_ADD = 0;

		/** 请求删除 */
		byte ACTION_REQ_DELETE = 1;

		/** 审核中 */
		byte STATUS_INPROGRESS = 0;
		/** 审核通过 */
		byte STATUS_PASS = 1;
		/** 审核拒绝 */
		byte STATUS_DECLINE = 2;
	}

	interface CRecord {
		byte DB_INSERTED = 0;// "刚插入数据库，尚未被后台更新到DNS"
		byte BG_UPDATED = 1;// "被后台更新到DNS，现在正常工作中",
		byte DB_DELETED = 2;// "数据库里被标记为删除，尚未被后台删除",
		byte NOT_EXIST = 3;// "不存在";

		String TYPE_A = "A";
		String TYPE_CNAME = "CNAME";
		String TYPE_MX = "MX";
		String TYPE_NS = "NS";
		String TYPE_TXT = "TXT";
		String TYPE_AAAA = "AAAA";
		List<String> TYPES = Arrays.asList(TYPE_A, TYPE_CNAME, TYPE_MX, TYPE_NS, TYPE_TXT, TYPE_AAAA);

		String ISP_TEL = "tel";
		String ISP_UNI = "uni";
		String ISP_ANY = "any";

	}

	interface CUserLog {
		int Type_LOGIN = 1; // 登陆
		int Type_ADD_ZONE = 2; // 增加zone
		int Type_DELETE_ZONE = 3; // 删除zone
		int Type_ADD_DNSRECORD = 4; // 增加dns记录
		int Type_UPDATE_DNSRECORD = 5; // 修改dns记录
		int Type_DELETE_DNSRECORD = 6; // 删除dns记录
		int Type_AUTHORIZE = 7; // 授权
		int Type_AUTHORIZED = 8; // 被授权
		int Type_DELETE_USER = 9; // 删除用户
		int Type_MOD_PASSWORD = 10; // 修改密码
		int Type_DELETE_AUTHORIZE = 11; // 解除授权
		int Type_ADD_WSAUTHORIZE = 12; // 添加外部服务认证
		int Type_MOD_WSAUTHORIZE = 13; // 修改外部服务认证
		int Type_DEL_WSAUTHORIZE = 14; // 去除外部服务认证

		int TYPE_APPLY_ADD_ZONE = 15; // 申请创建Zone
		int TYPE_APPLY_DEL_ZONE = 16; // 申请删除Zone
		int TYPE_DELED_ZONE = 17; // 管理员批准删除Zone
		int TYPE_ADDED_ZONE_PRIV = 18; // 被增加Zone权限
		int TYPE_DELED_ZONE_PRIV = 19;// 被移除Zone权限
		int TYPE_ADD_DNS_RECORD = 20; // 增加DNS记录
		int TYPE_UPD_DNS_RECORD = 21; // 修改DNS记录
		int TYPE_DEL_DNS_RECORD = 22; // 删除DNS记录
		int TYPE_UPD_USER_TOKEN = 23; // 修改用户Token
		int TYPE_APPROVE_ADD_ZONE = 24;// 批准创建Zone
		int TYPE_APPROVE_DEL_ZONE = 35;// 批准删除Zone
	}

	interface CZone extends CRecord {

	}

	interface CUserZone extends CZoneApply {

	}

}
