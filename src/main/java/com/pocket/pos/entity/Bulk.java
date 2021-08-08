package com.pocket.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "pos_bulk",uniqueConstraints = {@UniqueConstraint(columnNames = {"buy_price","product_id"})})

public class Bulk extends EntityCommons {

	@Column(nullable = false,name = "buy_price")
	private Double buyPrice;
	@Column(nullable = false,name = "quantity")
	private Double quantity;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public Bulk() {

	}

	public Bulk(Double buyPrice, Double quantity, Product product) {
		super();
		this.buyPrice = buyPrice;
		this.quantity = quantity;
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(Double quantity) {
		this.quantity += quantity;
	}
	
	public void subtractQuantity(Double quantity) {
		this.quantity -= quantity;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(buyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
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
		if (createDateTime == null) {
			if (other.getCreateDateTime() != null) {
				return false;
			}
		} else if (!createDateTime.equals(other.getCreateDateTime())) {
			return false;
		}
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.getQuantity())) {
			return false;
		}
		return true;
	}

}
