package com.yy.game.cloudns.sdk;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yy.game.cloudns.object.CloudnsApiConstants;
import com.yy.game.cloudns.object.Message;
import com.yy.game.cloudns.object.SliceResult;
import com.yy.game.cloudns.object.User;
import com.yy.game.cloudns.security.CheckCodeGenerator;

public class ExtApiTest {

	CloudnsExtApiClient client;

	@Before
	public void before() {
		client = new CloudnsExtApiClient();
		client.setApiUrl("http://localhost/v1.2/api/");
		client.setPsp("duowanadmin");
		client.setTkn("123");
		client.setCheckCode(CheckCodeGenerator.getInstance());
	}

	@Test
	public void extUserLoad() throws IOException {
		System.out.println("extUserLoad");
		Message<User> msg = client.extUserLoad("duowanadmin");
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			System.out.println(formatUser(msg.getResult()));
		}
	}

	@Test
	public void extUserNew() throws IOException {
		System.out.println("extUserNew");
		Message<Integer> msg = client.extUserNew("dw_hezhaowu", CloudnsApiConstants.CUser.UTYPE_USER);
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			System.out.println(msg.getResult());
		}
	}

	@Test
	public void userlogLoadAll() throws IOException {
		System.out.println("userlogLoadAll");
		Message<SliceResult<User>> msg = client.extUserLoadAll();
		if (msg.getErrno() != 0) {
			System.out.println("ERROR:" + msg.getErrmsg());
		} else {
			List<User> data = msg.getResult().getData();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					System.out.println(i + "=>" + formatUser(data.get(i)));
				}
			}
		}
	}

	private String formatUser(User user) {
		if (user == null) {
			return null;
		}
		return String.format("{id=%s,name=%s,type=%s,ctime=%s,tkn=%s}", user.getId(), user.getName(), user.getType(), user.getCtime(), user.getToken());
	}

}
