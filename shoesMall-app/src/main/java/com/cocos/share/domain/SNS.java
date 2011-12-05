package com.cocos.share.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sns")
public class Sns extends SupperEntity {
	private static final long serialVersionUID = 5832521870915458923L;
	private Long id;
	private String name;
	private String briefName;
	private String description;

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

	public Sns() {
	}

	public Sns(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "BRIEF_NAME")
	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}
}