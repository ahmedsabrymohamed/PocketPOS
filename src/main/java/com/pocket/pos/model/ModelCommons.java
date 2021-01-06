package com.pocket.pos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class ModelCommons {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;
	@Version
	protected Long version;
	@CreationTimestamp
	protected LocalDateTime createDateTime;
	@UpdateTimestamp
	protected LocalDateTime updateDateTime;
	@Column(nullable = false)
	protected boolean deleted = false;
	
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Long getId() {
		return id;
	}
	public Long getVersion() {
		return version;
	}
	
	
}
