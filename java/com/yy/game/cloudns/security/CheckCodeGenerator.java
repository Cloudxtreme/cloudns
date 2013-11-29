package com.yy.game.cloudns.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 验证码算法
 * 
 * @author jason.he
 * @date 2013-11-15
 */
public class CheckCodeGenerator implements ICheckCode {

	static CheckCodeGenerator singleton = new CheckCodeGenerator();

	public String hexCode(String psp, String tkn, String a) {
		String salt = instances[index(a, tkn)].hexCode(psp, tkn, a);
		return DigestUtils.md5Hex(psp + salt);
	}

	public static CheckCodeGenerator getInstance() {
		return singleton;
	}

	static int index(String psp, String tkn) {
		int s = 0;
		for (int i = 0, n = psp.length(); i < n; i++) {
			s += psp.charAt(i);
		}
		for (int i = 0, n = tkn.length(); i < n; i++) {
			s += tkn.charAt(i);
		}
		return s % instances.length;
	}

	static ICheckCode[] instances = { new ICheckCode() {
		@Override
		public String hexCode(String psp, String tkn, String a) {
			return DigestUtils.md5Hex(a + tkn);
		}
	}, new ICheckCode() {
		@Override
		public String hexCode(String psp, String tkn, String a) {
			return DigestUtils.shaHex(a + tkn);
		}
	}, new ICheckCode() {
		@Override
		public String hexCode(String psp, String tkn, String a) {
			return DigestUtils.sha256Hex(a + tkn);
		}
	}, new ICheckCode() {
		@Override
		public String hexCode(String psp, String tkn, String a) {
			return DigestUtils.sha384Hex(a + tkn);
		}
	}, new ICheckCode() {
		@Override
		public String hexCode(String psp, String tkn, String a) {
			return DigestUtils.sha512Hex(a + tkn);
		}
	} };

}
