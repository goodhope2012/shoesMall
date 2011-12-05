package com.face4j.facebook.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpPostUtil {
	URL url;
	HttpURLConnection conn;
	String boundary = "--------httppost123";
	Map<String, String> textParams = new HashMap<String, String>();
	byte[] file;
	ByteArrayOutputStream out;

	DataOutputStream ds;
	ByteArrayInputStream fileInputStream;

	public HttpPostUtil(String url) throws Exception {
		this.url = new URL(url);
	}

	public void addTextParameter(String name, String value) {
		textParams.put(name, value);
	}

	// 增加一个文件到form表单数据中
	public void addFileParameter(byte[] file) {
		this.file = file;
	}

	// 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
	public String send() {
		String serverResponseMessage = "";
		try {
			initConnection();
			conn.connect();
			ds = new DataOutputStream(conn.getOutputStream());
			writeFileParams();
			writeStringParams();
			paramsEnd();
			int serverResponseCode = conn.getResponseCode();
			serverResponseMessage = conn.getResponseMessage();
			InputStream in = conn.getInputStream();
			out = new ByteArrayOutputStream();
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			conn.disconnect();
			String valueOf = new String(out.toByteArray());
			return valueOf;

		} catch (Exception e) {

		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
			if (ds != null) {
				try {
					ds.flush();
					ds.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}

			}
		}
		return serverResponseMessage;
	}

	// 文件上传的connection的一些必须设置
	private void initConnection() throws Exception {
		conn = (HttpURLConnection) this.url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(60000); // 连接超时为10秒
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	}

	// 普通字符串数据
	private void writeStringParams() throws Exception {
		Set<String> keySet = textParams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textParams.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n");
			ds.writeBytes("\r\n");
			ds.writeBytes(encode(value) + "\r\n");
		}
	}

	// 文件数据
	private void writeFileParams() throws Exception {
		if (file != null) {
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\"" + "shareImage" + "\"; filename=\"" + System.currentTimeMillis() + ".png" + "\"\r\n");
			ds.writeBytes("Content-Type: " + getContentType() + "\r\n");
			ds.writeBytes("\r\n");
			getBytes(file);
			ds.writeBytes("\r\n");
		}
	}

	// 获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
	private String getContentType() throws Exception {

		return "application/octet-stream";
		// 此行不再细分是否为图片，全部作为application/octet-stream 类型
		// ImageInputStream imagein = ImageIO.createImageInputStream(f);
		// if (imagein == null) {
		// return "application/octet-stream";
		// }
		// Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
		// if (!it.hasNext()) {
		// imagein.close();
		// return "application/octet-stream";
		// }
		// imagein.close();
		// return "image/" +
		// it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写

	}

	// 把文件转换成字节数组
	private void getBytes(byte[] file) throws Exception {
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		// FileInputStream fileInputStream = new FileInputStream(f);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(file);
		bytesAvailable = fileInputStream.available();
		bufferSize = Math.min(bytesAvailable, maxBufferSize);
		buffer = new byte[bufferSize];
		// Read file
		bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		while (bytesRead > 0) {
			ds.write(buffer, 0, bufferSize);
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		}
	}

	// 添加结尾数据
	private void paramsEnd() throws Exception {
		ds.writeBytes("--" + boundary + "--" + "\r\n");
		ds.writeBytes("\r\n");
	}

	// 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
	private String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

	// public void postImage() {
	//
	// String pathToOurFile = "/mnt/sdcard/sysdroid.png";// this will be the
	// // file path
	// String urlServer = "http://yourdomain/fileupload.aspx";
	// int bytesRead, bytesAvailable, bufferSize;
	// byte[] buffer;
	// int maxBufferSize = 1 * 1024 * 1024;
	// try {
	// FileInputStream fileInputStream = new FileInputStream(new
	// File(pathToOurFile));
	// URL url = new URL(urlServer);
	// HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	// // Allow Inputs & Outputs
	// connection.setDoInput(true);
	// connection.setDoOutput(true);
	// connection.setUseCaches(false);
	// // Enable POST method
	// connection.setRequestMethod("POST");
	// connection.setRequestProperty("Connection", "Keep-Alive");
	// connection.setRequestProperty("Content-Type", "multipart/form-data");
	// connection.setRequestProperty("SD-FileName", "sysdroid.png");
	// DataOutputStream outputStream = new
	// DataOutputStream(connection.getOutputStream());
	// bytesAvailable = fileInputStream.available();
	// bufferSize = Math.min(bytesAvailable, maxBufferSize);
	// buffer = new byte[bufferSize];
	// // Read file
	// bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	// while (bytesRead > 0) {
	// outputStream.write(buffer, 0, bufferSize);
	// bytesAvailable = fileInputStream.available();
	// bufferSize = Math.min(bytesAvailable, maxBufferSize);
	// bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	// }
	// int serverResponseCode = connection.getResponseCode();
	// String serverResponseMessage = connection.getResponseMessage();
	// fileInputStream.close();
	// outputStream.flush();
	// outputStream.close();
	// } catch (Exception ex) { // ex.printStackTrace();
	// }
	// }
}