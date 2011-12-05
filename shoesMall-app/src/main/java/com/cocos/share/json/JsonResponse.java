package com.cocos.share.json;

import java.io.Serializable;

public class JsonResponse implements Serializable {
	private static final long serialVersionUID = -3230171936471284504L;
	private int returnCode;
	private String returnMsg;
	private Object content;

	public JsonResponse(int returnCode, String returnMsg, Object content) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.content = content;
	}

	public JsonResponse(int returnCode, String returnMsg) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}