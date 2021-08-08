package com.pocket.pos.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pos_product")
public class Product extends EntityCommons {

	@Column(nullable = false,unique = true)
	private String name;
	private LocalDate expirationDate;
	
	public Product() {
		
	}
	
	public Product(String name, LocalDate expirationDate) {
		super();
		this.name = name;
		this.expirationDate = expirationDate;
		
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (createDateTime == null) {
			if (other.getCreateDateTime() != null) {
				return false;
			}
		} else if (!createDateTime.equals(other.getCreateDateTime())) {
			return false;
		}
		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		return true;
	}
	
	
	
	
	
}
