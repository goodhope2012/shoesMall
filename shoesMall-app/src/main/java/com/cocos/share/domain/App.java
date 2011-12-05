package com.cocos.share.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "app")
public class App extends SupperEntity {
	private static final long serialVersionUID = -374477086005366135L;
	private Long id;
	private String name;
	private String url;
	private String platform;
	private String category;
	private String description;
	@Column(name = "USER_ID")
	private User user;
	private String shareKey;
	private Date createdDate;
	private List<AppSns> appSnsList;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APP")
	@SequenceGenerator(name = "SEQ_APP", sequenceName = "SEQ_APP", allocationSize = 1)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public App() {
	}

	public App(String name, String description) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "SHARE_KEY")
	public String getShareKey() {
		return shareKey;
	}

	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToMany(mappedBy = "appId")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<AppSns> getAppSnsList() {
		return appSnsList;
	}

	public void setAppSnsList(List<AppSns> appSnsList) {
		this.appSnsList = appSnsList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}