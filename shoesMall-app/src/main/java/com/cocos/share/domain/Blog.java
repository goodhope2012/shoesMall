package com.cocos.share.domain;

import java.io.File;

public class Blog {
	private String content;
	private File file;

	public Blog(String content, File file) {
		super();
		this.content = content;
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}