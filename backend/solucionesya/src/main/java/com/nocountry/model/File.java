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

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "file")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class File implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, name = "id")
	private String id;

	@CreationTimestamp
	@Column(name = "creation_date", nullable = false)
	protected Date creationDate;

	@UpdateTimestamp
	@Column(name = "update_date")
	protected Date updateDate;

	@Basic
	@Column(name = "original_name")
	private String originalName;

	@Basic
	@Column(name = "file_name")
	private String fileName;

	@Column(name = "path")
	private String path;

	// RELATION FILE --> ADMIN
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	@ToString.Exclude
	private Admin admin;

	@Column(name = "soft_delete", nullable = false)
	private boolean softDelete = Boolean.FALSE;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		File file = (File) o;
		return id != null && Objects.equals(id, file.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}