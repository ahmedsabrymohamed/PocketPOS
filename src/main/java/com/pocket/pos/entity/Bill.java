package com.pocket.pos.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="pos_bill")
public  class Bill extends EntityCommons {
	
	@Column(nullable = false)
	private Double total;
	@Column(nullable = false)
	private Double paid;
	@Column(nullable = false)
	private Double remainder;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BillType billType;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private BillSecondParty secondParty;
	@ElementCollection(fetch = FetchType.LAZY )
	@CollectionTable(name = "pos_bill_item",uniqueConstraints = {@UniqueConstraint(columnNames = {"bill_id", "bulk_id"})})
	private Collection<BillItem> items = new ArrayList<>();
	
	

	public Bill() {
		
	}
	
	

	public Bill(Double paid,BillType billType, BillSecondParty secondParty) {
		super();
		
		this.paid = paid;
		this.billType = billType;
		this.secondParty = secondParty;
		
	}



	
	



	

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getRemainder() {
		return remainder;
	}

	public void setRemainder(Double remainder) {
		this.remainder = remainder;
	}

	public BillType getBillType() {
		return billType;
	}

	public void setBillType(BillType partyType) {
		this.billType = partyType;
	}
	

	public Collection<BillItem> getItems() {
		return items;
	}

	public void setItems(Collection<BillItem> items) {
		this.items = items;
	}

	public BillSecondParty getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(BillSecondParty secondParty) {
		this.secondParty = secondParty;
	}

	
	private void calculateTotal() {
		total = items.stream().reduce(0.0,(total,item)->total+= (item.getPrice()*item.getQuantity()),(a,b)->a+b);
	}
	
	private void calculateRemainder() {
		remainder = total - paid ; 
	}

	public void calculateBill() {
		calculateTotal();
		calculateRemainder();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Bill)) {
			return false;
		}
		Bill other = (Bill) obj;
		if (createDateTime == null) {
			if (other.getCreateDateTime() != null) {
				return false;
			}
		} else if (!createDateTime.equals(other.getCreateDateTime())) {
			return false;
		}
		return true;
	}

	

	
	
	
}
