package com.pocket.pos.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class BillItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	@Column(nullable = false)
	double price;
	@Column(nullable = false)
	double quantity;

	@OneToOne
	@JoinColumn(unique = true)
	Bulk bulk;

	
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

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Bulk)) {
			return false;
		}
		return ((Bulk) obj).buyPrice == this.price && ((Bulk) obj).quantity == this.quantity;
	}

}
