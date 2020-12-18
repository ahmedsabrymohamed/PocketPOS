package com.pocket.pos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "pos_bulk")
@Check(constraints = "quantity >= 0")
public class Bulk {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable = false)
	private Double buyPrice;
	@Column(nullable = false)
	private Long quantity;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private Product product;
	@CreationTimestamp
	private LocalDateTime creatDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	@Column(nullable = false)
	private boolean deleted = false;
	
	
	
	public Bulk() {
		
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getId() {
		return id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(buyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((creatDateTime == null) ? 0 : creatDateTime.hashCode());
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Bulk)) {
			return false;
		}
		Bulk other = (Bulk) obj;
		if (Double.doubleToLongBits(buyPrice) != Double.doubleToLongBits(other.getBuyPrice())) {
			return false;
		}
		if (creatDateTime == null) {
			if (other.getCreatDateTime() != null) {
				return false;
			}
		} else if (!creatDateTime.equals(other.getCreatDateTime())) {
			return false;
		}
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.getQuantity())) {
			return false;
		}
		return true;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	
	
	
}
