package com.pocket.pos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "pos_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	@Column(nullable = false)
	String name;
	LocalDate expirationDate;
	@CreationTimestamp
	LocalDateTime creatDateTime;
	@UpdateTimestamp
	LocalDateTime updateDateTime;
	@Column(nullable = false)
	boolean deleted;
	
	
	
	public Product() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public LocalDateTime getCreatDateTime() {
		return creatDateTime;
	}
	public void setCreatDateTime(LocalDateTime creatDateTime) {
		this.creatDateTime = creatDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public Long getId() {
		return id;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
