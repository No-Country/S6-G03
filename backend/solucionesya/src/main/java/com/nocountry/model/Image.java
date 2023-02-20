package com.nocountry.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "images")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Image {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, name = "id")
	private String id;

	@Basic
	@Column(name = "original_name")
	private String originalName;

	@Basic
	@Column(name = "image_name")
	private String imageName;

	@Column(name = "path")
	private String path;

	@CreationTimestamp
	@Column(name = "creation_date", nullable = false)
	protected Date creationDate = new Date();

	@UpdateTimestamp
	@Column(name = "update_date")
	protected Date updateDate;

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

	@Column(name = "soft_delete", nullable = false)
	private boolean softDelete = Boolean.FALSE;

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