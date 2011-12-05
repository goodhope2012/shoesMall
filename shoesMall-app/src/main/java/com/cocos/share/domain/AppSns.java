package com.cocos.share.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "APP_SNS")
public class AppSns extends SupperEntity {
	private static final long serialVersionUID = -7099727263281382487L;
	private Long id;
	private Long appId;
	private Sns sns;
	private String snsAccount;
	private String snsKey;
	private String snsSecret;
	private String templet;
	private Date createdDate;

	public AppSns() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SNS")
	@SequenceGenerator(name = "SEQ_SNS", sequenceName = "SEQ_SNS", allocationSize = 1)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "APP_ID")
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@Column(name = "SNS_KEY")
	public String getSnsKey() {
		return snsKey;
	}

	public void setSnsKey(String snsKey) {
		this.snsKey = snsKey;
	}

	@Column(name = "SNS_SECRET")
	public String getSnsSecret() {
		return snsSecret;
	}

	public void setSnsSecret(String snsSecret) {
		this.snsSecret = snsSecret;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

	@Column(name = "SNS_ACCOUNT")
	public String getSnsAccount() {
		return snsAccount;
	}

	public void setSnsAccount(String snsAccount) {
		this.snsAccount = snsAccount;
	}

	@ManyToOne
	@JoinColumn(name = "SNS_ID")
	public Sns getSns() {
		return sns;
	}

	public void setSns(Sns sns) {
		this.sns = sns;
	}
}