package com.nocountry.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "images")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Image {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, name = "id")
	private String id;

	@Column(name = "original_name")
	private String originalName;

	@Column(name = "path")
	private String path;

	@Column(name = "cloudinary_id")
	private String cloudinaryId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

	// RELATION FILE --> ADMIN
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	@ToString.Exclude
	private Admin admin;

	// RELATION FILE --> PROVIDER
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id")
	@ToString.Exclude
	private Provider provider;

	// RELATION IMAGE --> PROVISION
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provision_id")
	@ToString.Exclude
	private Provision provision;

	public Image() {
	}

	public Image(String originalName, String path, String cloudinaryId) {
		this.originalName = originalName;
		this.path = path;
		this.cloudinaryId = cloudinaryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Image image = (Image) o;
		return id != null && Objects.equals(id, image.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}