package com.cocos.share.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_BEHAVIOR")
public class UserBehavior extends SupperEntity {
	private static final long serialVersionUID = -2224943729011803967L;
	private Long id;
	private String shareKey;
	private String imei;
	private Date date = new Date();
	private String action;
	private String result;
	private String additional;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_BEHAVIOR")
	@SequenceGenerator(name = "SEQ_USER_BEHAVIOR", sequenceName = "SEQ_USER_BEHAVIOR", allocationSize = 1)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SHARE_KEY")
	public String getShareKey() {
		return shareKey;
	}

	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}