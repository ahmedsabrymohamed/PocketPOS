package com.pocket.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bulk {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	@Column(nullable = false)
	double buyPrice;
	@Column(nullable = false)
	double quantity;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	Product product;
	
	
	
	public Bulk() {
		
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
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
	@Override
	public boolean equals(Object obj) {
		 if (obj == null || !(obj instanceof Bulk)) { 
	            return false; 
	        } 
		return ((Bulk)obj).buyPrice == this.buyPrice && ((Bulk)obj).quantity == this.quantity;
	}
	
	
}
