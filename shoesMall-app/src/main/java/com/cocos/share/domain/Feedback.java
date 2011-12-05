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
@Table(name = "FEEDBACK")
public class Feedback extends SupperEntity {
	private static final long serialVersionUID = 2837395318409084339L;
	private Long id;
	private Date date = new Date();
	private String email;
	private String tel;
	private String topic;
	private String additional;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FEEDBACK")
	@SequenceGenerator(name = "SEQ_FEEDBACK", sequenceName = "SEQ_FEEDBACK", allocationSize = 1)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}