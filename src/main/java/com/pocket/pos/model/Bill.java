package com.pocket.pos.model;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="pos_bill")
public  class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	@Column(nullable = false , unique = true)
	int billNumber;
	@Column(nullable = false)
	Long total;
	@Column(nullable = false)
	Long paid;
	@Column(nullable = false)
	Long remainder;
	@CreationTimestamp
	LocalDateTime creatDateTime;
	@UpdateTimestamp
	LocalDateTime updateDateTime;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	BillType billType;
	@Column(nullable = false)
	boolean deleted;
	@ManyToOne(optional = false)
	BillSecondParty secondParty;
	@ElementCollection
	@CollectionTable(name = "Bill_Item")
	Collection<BillItem> items;
	
	

	public Bill() {
		
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
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

	public long getId() {
		return id;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPaid() {
		return paid;
	}

	public void setPaid(Long paid) {
		this.paid = paid;
	}

	public Long getRemainder() {
		return remainder;
	}

	public void setRemainder(Long remainder) {
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public BillSecondParty getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(BillSecondParty secondParty) {
		this.secondParty = secondParty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + billNumber;
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
		if (billNumber != other.billNumber) {
			return false;
		}
		return true;
	}

	
	
	
}
