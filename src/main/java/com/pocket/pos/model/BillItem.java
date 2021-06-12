package com.pocket.pos.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class BillItem {

	
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Double quantity;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Bulk bulk;

	
	public BillItem() {
		
	}


	public BillItem(Double price, Double quantity, Bulk bulk) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.bulk = bulk;
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


	public Bulk getBulk() {
		return bulk;
	}


	public void setBulk(Bulk bulk) {
		this.bulk = bulk;
	}
	
	

	

	

}
