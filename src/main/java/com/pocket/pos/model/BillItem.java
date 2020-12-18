package com.pocket.pos.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class BillItem {

	
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Double quantity;

	@OneToOne
	@JoinColumn(unique = true)
	private Bulk bulk;

	
	public BillItem() {
		
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double buyPrice) {
		this.price = buyPrice;
	}

	

	

}
