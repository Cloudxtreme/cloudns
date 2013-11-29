package com.yy.game.cloudns.sdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpUtils {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static String readJsonMV(String url, Map<String, Object> mparams, Object... vparams) throws IOException {
		StringBuilder sb = new StringBuilder();
		if (mparams != null && mparams.size() > 0) {
			for (Map.Entry<String, Object> entry : mparams.entrySet()) {
				Object val = entry.getValue();
				sb.append(entry.getKey()).append('=').append(URLEncoder.encode(val == null ? "" : val.toString(), DEFAULT_CHARSET.name())).append('&');
			}
		}
		if (vparams != null && vparams.length > 0) {
			for (int i = 1; i < vparams.length; i += 2) {
				String key = (String) vparams[i - 1];
				Object val = vparams[i];
				sb.append(key).append('=').append(URLEncoder.encode(val == null ? "" : val.toString(), DEFAULT_CHARSET.name())).append('&');
			}
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return readJsons(url, sb.toString());
	}

	public static String readJsonV(String url, Object... vparams) throws IOException {
		StringBuilder sb = new StringBuilder();
		if (vparams != null && vparams.length > 0) {
			for (int i = 1; i < vparams.length; i += 2) {
				String key = (String) vparams[i - 1];
				Object val = vparams[i];
				sb.append(key).append('=').append(URLEncoder.encode(val == null ? "" : val.toString(), DEFAULT_CHARSET.name())).append('&');
			}
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return readJsons(url, sb.toString());
	}

	public static String readJsonM(String url, Map<String, Object> mparams) throws IOException {
		StringBuilder sb = new StringBuilder();
		if (mparams != null && mparams.size() > 0) {
			for (Map.Entry<String, Object> entry : mparams.entrySet()) {
				Object val = entry.getValue();
				sb.append(entry.getKey()).append('=').append(URLEncoder.encode(val == null ? "" : val.toString(), DEFAULT_CHARSET.name())).append('&');
			}
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return readJsons(url, sb.toString());
	}

	private static String readJsons(String url, String params) throws IOException {

		HttpURLConnection conn = null;
		try {

			conn = getHttpConnection(url);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();

			if (params != null && params.trim().length() > 0) {
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(params);
				out.close();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder json = new StringBuilder();
			char[] cbuf = new char[1024];
			int len = 0;
			while ((len = in.read(cbuf)) > 0) {
				json.append(cbuf, 0, len);
			}
			in.close();

			return json.toString();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	static HttpURLConnection getHttpConnection(String url) throws MalformedURLException, IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		if (url.startsWith("https")) {
			((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
		}
		return conn;
	}

	static SSLSocketFactory ssf;
	static {
		InputStream in = null;
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			in = HttpUtils.class.getResourceAsStream("/duowan.com.keystore");
			ks.load(in, "cloudns".toCharArray());
			SSLContext context = SSLContext.getInstance("TLS");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
			CloudnsTrustManager tm = new CloudnsTrustManager(defaultTrustManager);
			context.init(null, new TrustManager[] { tm }, null);
			ssf = context.getSocketFactory();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	static {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				return urlHostName != null && urlHostName.equals(session.getPeerHost());
			}
		});
	}

	static class CloudnsTrustManager implements X509TrustManager {

		X509TrustManager tm;

		CloudnsTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			tm.checkServerTrusted(chain, authType);
		}
	}

}
