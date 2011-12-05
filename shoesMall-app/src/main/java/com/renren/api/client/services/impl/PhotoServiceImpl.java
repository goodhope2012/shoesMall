package com.renren.api.client.services.impl;

import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.PhotoService;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-6-8
 */
public class PhotoServiceImpl extends AbstractService implements PhotoService {
	private static final Logger LOGGER = Logger.getLogger(PhotoServiceImpl.class);
	private static final String CONTENT_TYPE_BMP = "image/bmp";

	private static final String CONTENT_TYPE_PNG = "image/png";

	private static final String CONTENT_TYPE_GIF = "image/gif";

	private static final String CONTENT_TYPE_JPEG = "image/jpeg";

	private static final String CONTENT_TYPE_JPG = "image/jpg";

	public PhotoServiceImpl(RenrenApiInvoker renrenApiInvoker) {
		super(renrenApiInvoker);
	}

	public JSONObject upload(long albumId, byte[] photo, String fileName, String description) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("method", "photos.upload");
		params.put("aid", String.valueOf(albumId));
		params.put("caption", description);

		String contentType = parseContentType(fileName);
		String contnet = this.renrenApiInvoker.uploadPhoto(params, fileName, contentType, photo);
		LOGGER.info("Json response = "+contnet);
		return (JSONObject) JSONValue.parse(contnet);
	}

	private String parseContentType(String fileName) {
		String contentType = "image/jpg";
		fileName = fileName.toLowerCase();
		if (fileName.endsWith(".jpg"))
			contentType = CONTENT_TYPE_JPG;
		else if (fileName.endsWith(".png"))
			contentType = CONTENT_TYPE_PNG;
		else if (fileName.endsWith(".jpeg"))
			contentType = CONTENT_TYPE_JPEG;
		else if (fileName.endsWith(".gif"))
			contentType = CONTENT_TYPE_GIF;
		else if (fileName.endsWith(".bmp"))
			contentType = CONTENT_TYPE_BMP;
		else
			throw new RuntimeException("不支持的文件类型'" + fileName + "'(或没有文件扩展名)");
		return contentType;
	}
}
